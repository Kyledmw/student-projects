#!/bin/sh
echo 0 | sudo tee /proc/sys/kernel/randomize_va_space
sudo bash -c 'echo "kernel.randomize_va_space = 0" >> /etc/sysctl.conf'
sudo sysctl -p
