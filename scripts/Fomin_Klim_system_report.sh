#!/bin/bash

LOG_FILE="logs/Fomin_Klim_system_report.log"

{
echo "=============================================="
echo "       Student System Report"
echo "=============================================="
echo "Student:          Klim Fomin"
echo "Date and Time:    $(date '+%Y-%m-%d %H:%M:%S')"
echo "Username:         $(whoami)"
echo "Operating System: $(uname -s)"
echo "Kernel Version:   $(uname -r)"
echo ""
echo "----- CPU Information -----"
lscpu | grep -E "Model name|CPU\(s\)|Thread|Core"
echo ""
echo "----- Memory Information -----"
free -h
echo ""
echo "----- Disk Usage -----"
df -h /
echo ""
echo "----- Available Disk Space -----"
df -h / | tail -1 | awk '{print "Available: " $4}'
echo "=============================================="
} | tee -a "$LOG_FILE"
