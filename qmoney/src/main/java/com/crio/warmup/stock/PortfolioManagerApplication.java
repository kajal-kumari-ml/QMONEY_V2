
package com.crio.warmup.stock;


import com.crio.warmup.stock.dto.*;
import com.crio.warmup.stock.log.UncaughtExceptionHandler;
import com.crio.warmup.stock.portfolio.PortfolioManager;
import com.crio.warmup.stock.portfolio.PortfolioManagerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import com.crio.warmup.stock.dto.AnnualizedReturn;
import com.crio.warmup.stock.dto.Candle;
import com.crio.warmup.stock.dto.PortfolioTrade;
import com.crio.warmup.stock.dto.TiingoCandle;
import com.crio.warmup.stock.dto.TotalReturnsDto;
import com.crio.warmup.stock.log.UncaughtExceptionHandler;
import com.crio.warmup.stock.portfolio.PortfolioManager;
import com.crio.warmup.stock.portfolio.PortfolioManagerFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.web.client.RestTemplate;


public class PortfolioManagerApplication {

  @Autowired
  ObjectMapper objectMapper;

  static String apiKey="99c33824ddb88e5924fadc898b552832ce7c98d7";

  public static String getToken(){
  return "99c33824ddb88e5924fadc898b552832ce7c98d7";
  }

  // TODO: CRIO_TASK_MODULE_JSON_PARSING
  //  Task:
  //       - Read the json file provided in the argument[0], The file is available in the classpath.
  //       - Go through all of the trades in the given file,
  //       - Prepare the list of all symbols a portfolio has.
  //       - if "trades.json" has trades like
  //         [{ "symbol": "MSFT"}, { "symbol": "AAPL"}, { "symbol": "GOOGL"}]
  //         Then you should return ["MSFT", "AAPL", "GOOGL"]
  //  Hints:
  //    1. Go through two functions provided - #resolveFileFromResources() and #getObjectMapper
  //       Check if they are of any help to you.
  //    2. Return the list of all symbols in the same order as provided in json.

  //  Note:
  //  1. There can be few unused imports, you will need to fix them to make the build pass.
  //  2. You can use "./gradlew build" to check if your code builds successfully.

  public static List<String> mainReadFile(String[] args) throws IOException, URISyntaxException {
    String filename = args[0]; 
    File file = resolveFileFromResources(filename);
   // byte[] fileData = Files.readAllBytes(filePath);
    ObjectMapper objectMapper = new ObjectMapper();
   // File file = new File("/home/crio-user/workspace/kajalkumari73411-ME_QMONEY_V2/qmoney/src/test/resources/assessments/trades.json");
    JsonNode tradesNode = objectMapper.readTree(file);
    
    List<String> symbols = new ArrayList<>();
        for (JsonNode tradeNode : tradesNode) {
            String symbol = tradeNode.get("symbol").asText();
            symbols.add(symbol);
        }
    return symbols;
  }





  // TODO: CRIO_TASK_MODULE_CALCULATIONS
  //  Now that you have the list of PortfolioTrade and their data, calculate annualized returns
  //  for the stocks provided in the Json.
  //  Use the function you just wrote #calculateAnnualizedReturns.
  //  Return the list of AnnualizedReturns sorted by annualizedReturns in descending order.

  // Note:
  // 1. You may need to copy relevant code from #mainReadQuotes to parse the Json.
  // 2. Remember to get the latest quotes from Tiingo API.









  // TODO: CRIO_TASK_MODULE_REST_API
  //  Find out the closing price of each stock on the end_date and return the list
  //  of all symbols in ascending order by its close value on end date.

  // Note:
  // 1. You may have to register on Tiingo to get the api_token.
  // 2. Look at args parameter and the module instructions carefully.
  // 2. You can copy relevant code from #mainReadFile to parse the Json.
  // 3. Use RestTemplate#getForObject in order to call the API,
  //    and deserialize the results in List<Candle>



  private static void printJsonObject(Object object) throws IOException {
    Logger logger = Logger.getLogger(PortfolioManagerApplication.class.getCanonicalName());
    ObjectMapper mapper = new ObjectMapper();
    logger.info(mapper.writeValueAsString(object));
  }

  private static File resolveFileFromResources(String filename) throws URISyntaxException {
    return Paths.get(
        Thread.currentThread().getContextClassLoader().getResource(filename).toURI()).toFile();
  }

  private static ObjectMapper getObjectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    return objectMapper;
  }


  // TODO: CRIO_TASK_MODULE_JSON_PARSING
  //  Follow the instructions provided in the task documentation and fill up the correct values for
  //  the variables provided. First value is provided for your reference.
  //  A. Put a breakpoint on the first line inside mainReadFile() which says
  //    return Collections.emptyList();
  //  B. Then Debug the test #mainReadFile provided in PortfoliomanagerApplicationTest.java
  //  following the instructions to run the test.
  //  Once you are able to run the test, perform following tasks and record the output as a
  //  String in the function below.
  //  Use this link to see how to evaluate expressions -
  //  https://code.visualstudio.com/docs/editor/debugging#_data-inspection
  //  1. evaluate the value of "args[0]" and set the value
  //     to the variable named valueOfArgument0 (This is implemented for your reference.)
  //  2. In the same window, evaluate the value of expression below and set it
  //  to resultOfResolveFilePathArgs0
  //     expression ==> resolveFileFromResources(args[0])
  //  3. In the same window, evaluate the value of expression below and set it
  //  to toStringOfObjectMapper.
  //  You might see some garbage numbers in the output. Dont worry, its expected.
  //    expression ==> getObjectMapper().toString()
  //  4. Now Go to the debug window and open stack trace. Put the name of the function you see at
  //  second place from top to variable functionNameFromTestFileInStackTrace
  //  5. In the same window, you will see the line number of the function in the stack trace window.
  //  assign the same to lineNumberFromTestFileInStackTrace
  //  Once you are done with above, just run the corresponding test and
  //  make sure its working as expected. use below command to do the same.
  //  ./gradlew test --tests PortfolioManagerApplicationTest.testDebugValues

  public static List<String> debugOutputs() {

     String valueOfArgument0 = "trades.json";
     String resultOfResolveFilePathArgs0 = "/path/to/trades.json";
     String toStringOfObjectMapper = "com.fasterxml.jackson.databind.ObjectMapper@123456";
     String functionNameFromTestFileInStackTrace = "PortfolioManagerApplicationTest.mainReadFile()";
     String lineNumberFromTestFileInStackTrace = "25";


    return Arrays.asList(new String[]{valueOfArgument0, resultOfResolveFilePathArgs0,
        toStringOfObjectMapper, functionNameFromTestFileInStackTrace,
        lineNumberFromTestFileInStackTrace});
  }


  // Note:
  // Remember to confirm that you are getting same results for annualized returns as in Module 3.
  public static List<String> mainReadQuotes(String[] args) throws IOException, URISyntaxException , RuntimeException{
    String filename = args[0];
    String endDate = args[1];
    List<String> sortedStocks = new ArrayList<>();

    try {
        List<PortfolioTrade> portfolioTrades = readTradesFromJson(filename);

        RestTemplate restTemplate = new RestTemplate();
        List<TotalReturnsDto> totalReturnsList = new ArrayList<>();

        LocalDate parsedEndDate = LocalDate.parse(endDate);

        for (PortfolioTrade trade : portfolioTrades) {
          LocalDate purchaseDate = LocalDate.parse(trade.getPurchaseDate().toString());

          if (purchaseDate.isAfter(parsedEndDate)) {
              throw new RuntimeException("Invalid dates: Purchase date is after the end date");
          }
            String url = prepareUrl(trade, LocalDate.parse(endDate), apiKey);
            TiingoCandle[] tiingoCandles = restTemplate.getForObject(url, TiingoCandle[].class);

            if (tiingoCandles != null && tiingoCandles.length > 0) {
                double closingPrice = tiingoCandles[tiingoCandles.length - 1].getClose();
                TotalReturnsDto totalReturnsDto = new TotalReturnsDto(trade.getSymbol(), closingPrice);
                totalReturnsList.add(totalReturnsDto);
            }
        }

        totalReturnsList.sort(Comparator.comparingDouble(TotalReturnsDto::getClosingPrice));

        for (TotalReturnsDto totalReturnsDto : totalReturnsList) {
            sortedStocks.add(totalReturnsDto.getSymbol());
        }
    } catch (RuntimeException exception) {
        throw exception;
    }

    return sortedStocks;
  }

 


  // TODO:
  //  After refactor, make sure that the tests pass by using these two commands
  //  ./gradlew test --tests PortfolioManagerApplicationTest.readTradesFromJson
  //  ./gradlew test --tests PortfolioManagerApplicationTest.mainReadFile
  public static List<PortfolioTrade> readTradesFromJson(String filename) throws IOException, URISyntaxException {
    File file = resolveFileFromResources(filename);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode tradesNode = objectMapper.readTree(file);

        List<PortfolioTrade> trades = new ArrayList<>();
        for (JsonNode tradeNode : tradesNode) {
            String symbol = tradeNode.get("symbol").asText();
            int quantity = tradeNode.get("quantity").asInt();
          //  String buyPrice = tradeNode.get("tradeType").asText();
            String purchaseDate = tradeNode.get("purchaseDate").asText();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            // Parse the string into a LocalDate object
            LocalDate date = LocalDate.parse(purchaseDate, formatter);
            trades.add(new PortfolioTrade(symbol, quantity, date));
        }
        return trades;
  }


  // TODO:
  //  Build the Url using given parameters and use this function in your code to cann the API.
  public static String prepareUrl(PortfolioTrade trade, LocalDate endDate, String token) {
    String ticker = trade.getSymbol();
    String startDate = trade.getPurchaseDate().toString();
    String url = String.format("https://api.tiingo.com/tiingo/daily/%s/prices?startDate=%s&endDate=%s&token=%s", ticker, startDate, endDate, token);
    return url;  
  }
  // TODO:
  //  ./gradlew test --tests ModuleThreeRefactorTest
  static Double getOpeningPriceOnStartDate(List<Candle> candles) {
      return candles.get(0).getOpen();
  }


  public static Double getClosingPriceOnEndDate(List<Candle> candles) {
    return candles.get(candles.size()-1).getClose();
  } 


  public static List<Candle> fetchCandles(PortfolioTrade trade, LocalDate endDate, String token) {
    String ticker = trade.getSymbol();
    String startDate = trade.getPurchaseDate().toString();
    RestTemplate restTemplate = new RestTemplate();
    String url = String.format("https://api.tiingo.com/tiingo/daily/%s/prices?startDate=%s&endDate=%s&token=%s", ticker, startDate, endDate, token);
    List<Candle> candles = new ArrayList<>();
    TiingoCandle[] tiingoCandles = restTemplate.getForObject(url, TiingoCandle[].class);
    for(TiingoCandle tiingoCandle: tiingoCandles){
      candles.add(tiingoCandle);
    }
    return candles;
  }

  // public static List<AnnualizedReturn> mainCalculateSingleReturn(String[] args) 
  //     throws IOException, URISyntaxException, RuntimeException {
  //       String file = args[0];
  //       String endDateString = args[1];
  //       LocalDate endDate = LocalDate.parse(endDateString);

  //       // Read trades from the file
  //       List<PortfolioTrade> trades = readTradesFromJson(file);

  //       // Calculate annualized returns for each trade and store them in a list
  //       List<AnnualizedReturn> annualizedReturns = new ArrayList<>();
  //       try{
  //       for (PortfolioTrade trade : trades) {
  //         if (trade.getPurchaseDate().isAfter(endDate)) {
  //           throw new RuntimeException("Invalid dates: Purchase date is after the end date");
  //       }
           
  //       List<Candle> candles = fetchCandles(trade, endDate, apiKey);
  //       Double sellPrice= getOpeningPriceOnStartDate(candles);
  //       Double buyPrice= getClosingPriceOnEndDate(candles);
  //           AnnualizedReturn annualizedReturn = calculateAnnualizedReturns(endDate, trade, sellPrice, buyPrice);
  //           annualizedReturns.add(annualizedReturn);
           
  //       }}catch(Exception e){
  //         e.getMessage();
  //       }
  //      Collections.sort(annualizedReturns, Comparator.comparing(AnnualizedReturn::getAnnualizedReturn).reversed());
  //       return annualizedReturns;
  //     }

  public static List<AnnualizedReturn> mainCalculateSingleReturn(String[] args)
      throws IOException, URISyntaxException {
      List<PortfolioTrade> portTrade=readTradesFromJson(args[0]);
      List<AnnualizedReturn> newList = new ArrayList<>();
      LocalDate date= LocalDate.parse(args[1]);

      for (PortfolioTrade temp: portTrade){
        List<Candle> candleList =fetchCandles(temp, date, getToken());
        AnnualizedReturn objNew=calculateAnnualizedReturns(date, temp, getOpeningPriceOnStartDate(candleList), getClosingPriceOnEndDate(candleList));
        newList.add(objNew);
      }

      Collections.sort(newList, new Comparator<AnnualizedReturn>() {

        @Override
        public int compare(AnnualizedReturn arg0, AnnualizedReturn arg1) {
          
          return (int) arg0.getAnnualizedReturn().compareTo(arg1.getAnnualizedReturn());
        }
        
      });

      Collections.reverse(newList);

     return newList;
  }


  // TODO: CRIO_TASK_MODULE_CALCULATIONS
  //  Return the populated list of AnnualizedReturn for all stocks.
  //  Annualized returns should be calculated in two steps:
  //   1. Calculate totalReturn = (sell_value - buy_value) / buy_value.
  //      1.1 Store the same as totalReturns
  //   2. Calculate extrapolated annualized returns by scaling the same in years span.
  //      The formula is:
  //      annualized_returns = (1 + total_returns) ^ (1 / total_num_years) - 1
  //      2.1 Store the same as annualized_returns
  //  Test the same using below specified command. The build should be successful.
  //     ./gradlew test --tests PortfolioManagerApplicationTest.testCalculateAnnualizedReturn

  public static AnnualizedReturn calculateAnnualizedReturns(LocalDate endDate,
      PortfolioTrade trade, Double buyPrice, Double sellPrice) {
        double totalReturn = (sellPrice - buyPrice) / buyPrice;

        // Calculate the number of years between the buy date and the end date
        double totalNumYears = ChronoUnit.DAYS.between(trade.getPurchaseDate(), endDate) / 365.245;

        // Calculate extrapolated annualized returns using the given formula
        double extrapolated = Math.pow(1.0 + totalReturn, 1.0 / totalNumYears) - 1;
        return new AnnualizedReturn(trade.getSymbol(), extrapolated, totalReturn);
  }




















  // TODO: CRIO_TASK_MODULE_REFACTOR
  //  Once you are done with the implementation inside PortfolioManagerImpl and
  //  PortfolioManagerFactory, create PortfolioManager using PortfolioManagerFactory.
  //  Refer to the code from previous modules to get the List<PortfolioTrades> and endDate, and
  //  call the newly implemented method in PortfolioManager to calculate the annualized returns.

  // Note:
  // Remember to confirm that you are getting same results for annualized returns as in Module 3.

  public static List<AnnualizedReturn> mainCalculateReturnsAfterRefactor(String[] args)
      throws Exception {
       String file = args[0];
       LocalDate endDate = LocalDate.parse(args[1]);
       String contents = readFileAsString(file);
       ObjectMapper objectMapper = getObjectMapper();
       List<PortfolioTrade> portfolioTrades = objectMapper.readValue(contents, new TypeReference<>() {});
       PortfolioManager portfolioManager = PortfolioManagerFactory.getPortfolioManager(new RestTemplate());
       return portfolioManager.calculateAnnualizedReturn(portfolioTrades, endDate);
      }

public static String readFileAsString(String filename) throws IOException {

    return new String(Files.readAllBytes(Paths.get(filename)));
 }

  public static void main(String[] args) throws Exception {
    Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler());
    ThreadContext.put("runId", UUID.randomUUID().toString());

    printJsonObject(mainReadFile(args));


    printJsonObject(mainReadQuotes(args));



    printJsonObject(mainCalculateSingleReturn(args));




    printJsonObject(mainCalculateReturnsAfterRefactor(args));



  }
}

