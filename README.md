# QMoney Pvt. Ltd

QMoney is a financial service company that offers world-class solutions in Portfolio Management, Investment Advisory, and Wealth Management. Founded in 2010 and headquartered in Bangalore, the company has gained the trust of 1 million+ clients to manage their finances and investment needs.

In recent years, the company has grown to become one of the pioneers of online trading by building the first-of-its-kind platform to make trading efficient and effortless for its customers.


Note from the Product Manager
Welcome to QMoney! Our customer base is expanding with over 100 accounts opening every day. Our portfolio managers assist clients by analyzing their stock portfolios and making buy/sell recommendations. Portfolio analysis can be very time-consuming. With the number of clients growing at a rapid rate, portfolio managers need tools to help analyze client portfolios efficiently. To do that, our designers have come up with the following user experience for portfolio managers:

What's included: 
1. Gradle file created from start.spring.io
2. Plugins for Spotbugs, Checkstyle and Jacoco included
3. Other dependencies like Mongo, MySql and redis.
4. Dockerfile to start mongo server and run the spring boot application within.

###Usage - 

1. To build the repository - 

From the repository root, 

1. run `./gradlew build test`run the build
2. run `./gradlew bootjar` to create executable jar. The jar will be located inside build directories.

To run inside docker container, use below commands

To build docker image, use the command below - `docker build -t your_tag_name  .`

To run the generated container, use this command - `docker run -p8080:8080 your_tag_name`. This will run the server on 8080 port.. You can change the ports as per your needs. 


License - 
While this repository is licensed under APACHE 2.0 license, It is mandatory for users to share the readme.md and License file along with the changes they do in the contents.