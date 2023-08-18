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
# Given taks

Create a project, a simple runnable local server, that exposes some endpoints which take arguments and return plain simple data after performing certain internal operations. The goal is to query data from the Narodowy Bank Polski's public APIs and return relevant information from them. User manual: http://api.nbp.pl/

Considerations
Table A only will be used for average exchange rates and table C for buy and sell rates. Reference: https://nbp.pl/en/statistic-and-financial-reporting/rates/
Operations and examples can be found in the manual.
Weekend dates or holidays, for example, do not return data.
Required operations
Provide a separate endpoint for each operation:

Given a date (formatted YYYY-MM-DD) and a currency code (list: https://nbp.pl/en/statistic-and-financial-reporting/rates/table-a/), provide its average exchange rate.
Given a currency code and the number of last quotations N (N <= 255), provide the max and min average value (every day has a different average).
Given a currency code and the number of last quotations N (N <= 255), provide the major difference between the buy and ask rate (every day has different rates).
Optional
The following features are not mandatory, but considered as a plus:

Unit/integration tests.
Docker image of the whole application.
Swagger UI or any other simple front-end (with e.g. React, Angular).
Guidelines
It shouldn't take more than 1 day.
Decide on how to decompose application layers.
Pay attention to overall coding style and errors handling.
Output format is not specified.
No authentication nor security needed.
Add readme with relevant information about how to run it and examples.
RESTful web API design reference: https://learn.microsoft.com/en-us/azure/architecture/best-practices/api-design

Criteria
Provide succinct and direct instructions in the main readme to start the server and test the operations.

Example
To start the server, run this command:
```
python -m http.server   
```

To query operation 1, run this command (which should have the value 5.2768 as the returning information):
curl http://localhost:8888/exchanges/GBP/2023-01-02
Attention will be put on correct behaviour of operations, application structure, and general code quality (readability, naming, etc.).
