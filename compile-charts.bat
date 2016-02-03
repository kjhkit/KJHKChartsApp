if not exist start mkdir start
javac -d "start" KJHKChartsApp.java
cd start
echo "Main-class: KJHKChartsApp" >> manifest.mf
jar -cmf mainfest.mf KJHKChartsApp.jar start