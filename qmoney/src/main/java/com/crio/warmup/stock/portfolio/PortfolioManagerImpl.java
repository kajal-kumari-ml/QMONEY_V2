
package com.crio.warmup.stock.portfolio;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.SECONDS;

import com.crio.warmup.stock.dto.AnnualizedReturn;
import com.crio.warmup.stock.dto.Candle;
import com.crio.warmup.stock.dto.PortfolioTrade;
import com.crio.warmup.stock.dto.TiingoCandle;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.springframework.web.client.RestTemplate;

public class PortfolioManagerImpl implements PortfolioManager {


  String apiKey="99c33824ddb88e5924fadc898b552832ce7c98d7";
  private RestTemplate restTemplate;


  // Caution: Do not delete or modify the constructor, or else your build will break!
  // This is absolutely necessary for backward compatibility
  protected PortfolioManagerImpl(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }


  //TODO: CRIO_TASK_MODULE_REFACTOR
  // 1. Now we want to convert our code into a module, so we will not call it from main anymore.
  //    Copy your code from Module#3 PortfolioManagerApplication#calculateAnnualizedReturn
  //    into #calculateAnnualizedReturn function here and ensure it follows the method signature.
  // 2. Logic to read Json file and convert them into Objects will not be required further as our
  //    clients will take care of it, going forward.

  // Note:
  // Make sure to exercise the tests inside PortfolioManagerTest using command below:
  // ./gradlew test --tests PortfolioManagerTest

  //CHECKSTYLE:OFF






  private Comparator<AnnualizedReturn> getComparator() {
    return Comparator.comparing(AnnualizedReturn::getAnnualizedReturn).reversed();
  }

  //CHECKSTYLE:OFF

  // TODO: CRIO_TASK_MODULE_REFACTOR
  //  Extract the logic to call Tiingo third-party APIs to a separate function.
  //  Remember to fill out the buildUri function and use that.


  public List<Candle> getStockQuote(String symbol, LocalDate from, LocalDate to)
      throws JsonProcessingException {
        String url = buildUri(symbol, from, to);
        List<Candle> candles = new ArrayList<>();
        TiingoCandle[] tiingoCandles = restTemplate.getForObject(url, TiingoCandle[].class);
        for(TiingoCandle tiingoCandle: tiingoCandles){
          candles.add(tiingoCandle);
        }
        return candles;
  }

  static Double getOpeningPriceOnStartDate(List<Candle> candles) {
    return candles.get(0).getOpen();
}


public static Double getClosingPriceOnEndDate(List<Candle> candles) {
  return candles.get(candles.size()-1).getClose();
} 


  protected String buildUri(String symbol, LocalDate startDate, LocalDate endDate) {
    String url = String.format("https://api.tiingo.com/tiingo/daily/%s/prices?startDate=%s&endDate=%s&token=%s", symbol, startDate, endDate, apiKey);
    return url;
  }

  @Override
  public List<AnnualizedReturn> calculateAnnualizedReturn(List<PortfolioTrade> portfolioTrades, LocalDate endDate) {
    
    List<AnnualizedReturn> newList = new ArrayList<>();
    
    try{
    for (PortfolioTrade temp:portfolioTrades ){
      List<Candle> candleList =getStockQuote(temp.getSymbol(), temp.getPurchaseDate(), endDate);
      AnnualizedReturn objNew=calculateAnnualizedReturns(endDate, temp, getOpeningPriceOnStartDate(candleList), getClosingPriceOnEndDate(candleList));
      newList.add(objNew);
    }

    Collections.sort(newList, new Comparator<AnnualizedReturn>() {

      @Override
      public int compare(AnnualizedReturn arg0, AnnualizedReturn arg1) {
        
        return (int) arg0.getAnnualizedReturn().compareTo(arg1.getAnnualizedReturn());
      }
      
    });
  }catch(JsonProcessingException exception){
       exception.getMessage();
  }
    Collections.reverse(newList);

   return newList;
  }

  public static AnnualizedReturn calculateAnnualizedReturns(LocalDate endDate,
      PortfolioTrade trade, Double buyPrice, Double sellPrice) {
        double totalReturn = (sellPrice - buyPrice) / buyPrice;

        // Calculate the number of years between the buy date and the end date
        double totalNumYears = ChronoUnit.DAYS.between(trade.getPurchaseDate(), endDate) / 365.245;

        // Calculate extrapolated annualized returns using the given formula
        double extrapolated = Math.pow(1.0 + totalReturn, 1.0 / totalNumYears) - 1;
        return new AnnualizedReturn(trade.getSymbol(), extrapolated, totalReturn);
  }



}
