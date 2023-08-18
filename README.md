# DynatraceInternship

I would like you to visit my Render website

Sample requests for each mapping:

1) https://dynatraceinternship.onrender.com/average/currency/usd/date/2023-04-25

2) https://dynatraceinternship.onrender.com/difference/currency/usd/quotation/58
   
3) https://dynatraceinternship.onrender.com/extreme/currency/usd/quotation/80
   
API Design & Documentation:

1) https://dynatraceinternship.onrender.com/swagger-ui.html

  ️
You can also build project

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
