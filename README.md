# DynatraceInternship

build

In project root directory(same level as README.me) run in terminal: 

```
docker build . -t local
```

then type below command to run server

```
docker run -d -p 8080:8080 local 
```

to stop runnings docker containers

```
docker stop $(docker container ls -a -q --filter ancestor=local)
```

to remove containers 

```
docker rm $(docker container ls -a -q --filter ancestor=local)
```
