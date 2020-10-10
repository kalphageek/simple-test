## 개요
1. ssh.exec : ssh를 이용해 remote server에서 command 실행하고 collback 받아 print out 한다.
2. ssh.readfile : ssh의 sftp를 이용해서 원격 파일을 OutputStream으로 읽는다.
3. ssh.download : ssh의 sftp를 이용해 원격 directory를 local로 download 한다.
4. ssh.readfile2 : ssh 의 sftp를 이용해 사전선택된 원격디렉토리 하위의 파일목록을 만들고, 해당 파일들을 OutputStream으로 읽는다.