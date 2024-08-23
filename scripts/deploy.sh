REPOSITORY=/home/ubuntu
PROJECT_NAME=seolyu

echo "> Build 파일 복사"

cp $REPOSITORY/$PROJECT_NAME/build/libs/*.jar $REPOSITORY/

echo "> 현재 구동중인 애플리케이션 pid 확인"
CURRENT_PID=$(pgrep -f .jar)

echo "> 현재 구동중인 애플리케이션 pid : $CURRENT_PID" >> $REPOSITORY/deploy.log

if [ -z $CURRENT_PID ]
then
  echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다." >> $REPOSITORY/deploy.log
else
  echo "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

echo "> 새애플리케이션 배포"
JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR Name : $JAR_NAME"
echo "> $JAR_NAME 에 실행권한 추가"

chmod +x $JAR_NAME

echo "> $JAR_NAME 실행" >> $REPOSITORY/deploy.log

nohup java -jar \
  -Dspring.profiles.active=prd  \
  $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &

echo "6. 배포 완료"
