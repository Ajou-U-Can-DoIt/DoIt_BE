#!/bin/bash

REPOSITORY=/home/ec2-user/app/step2
PORT=8080

echo "> Build 파일 복사"

cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> 현재 구동중인 애플리케이션 pid 확인"

CURRENT_PID=$(netstat -lntp | grep $PORT | awk '{print $7}' | cut -f 1 -d'/')

echo "현재 구동 중인 애플리케이션 pid: $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
   echo "> 현재 구동 중인 애플리케이션이 없으므로 종료하지 않습니다."
else
   echo "> kill -15 $CURRENT_PID"
   kill -15 $CURRENT_PID
   sleep 5LIVE

fi

echo "> 새 어플리케이션 배포"

JAR_NAME=$(ls -tr $REPOSITORY/*.jar | haed -n 1)

echo "> JAR Name: $JAR_NAME"

echo "> $JAR_NAME 에 실행권한 추가"

chmod +x $JAR_NAME
sleep 1

echo "> $JAR_NAME 실행"

nohup java -jar $JAR_NAME >> /home/ec2-user/deploy.log 2>/home/ec2-user/deploy_err.log &