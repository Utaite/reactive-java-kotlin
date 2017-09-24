package chapter5.example;

import common.CommonUtils;
import common.Log;
import common.OkHttpHelper;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class OpenWeatherMapV1 {

    private static final String URL =
            "http://api.openweathermap.org/data/2.5/weather?q=london&APPID=";

    private static final String API_KEY =
            "0a2bb8c0d43e212ec8ebd04613b183a3";

    public void run() {
        Observable<String> source = Observable.just(URL + API_KEY)
                .map(OkHttpHelper::getWithLog)
                .subscribeOn(Schedulers.io());

        Observable<String> temperature = source.map(this::parseTemperature);
        Observable<String> city = source.map(this::parseCityName);
        Observable<String> country = source.map(this::parseCountry);
        CommonUtils.exampleStart();

        Observable.concat(temperature, city, country)
                .observeOn(Schedulers.newThread())
                .subscribe(Log::it);
        CommonUtils.sleep(10000);
    }

    private String parseTemperature(String json) {
        return parse(json, "\"temp\":[0-9]*.[0-9]*");
    }

    private String parseCityName(String json) {
        return parse(json, "\"name\":\"[a-zA-Z]*\"");
    }

    private String parseCountry(String json) {
        return parse(json, "\"country\":\"[a-zA-Z]*\"");
    }

    private String parse(String json, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(json);
        if(matcher.find()) {
            return matcher.group();
        }
        return "N/A";
    }

    public static void main(String[] args) {
        OpenWeatherMapV1 demo = new OpenWeatherMapV1();
        demo.run();
    }

}
