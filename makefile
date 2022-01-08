HADOOP = /usr/local/hadoop/bin/hadoop

APP = HadoopServer.jar
SRC = HadoopServer.java 
OUT = output

$(APP): $(SRC)
	mkdir -p $(OUT)
	javac -classpath .:opencsv-5.1.jar:commons-lang3-3.1.jar:'$(HADOOP) classpath' -d $(OUT) $(SRC)
	jar -cvf $(APP) -C $(OUT) .

test:
	$(HADOOP) $(APP) $(OUT)/HadoopServer

clean:
	rm -rf $(OUT)  $(APP).
