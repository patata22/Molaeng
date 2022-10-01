package idle.molaeng_back.global.api;


import idle.molaeng_back.recipe.model.entity.Ingredient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;

@Component
public class Crawler {

    String URL = "https://www.ssg.com/search.ssg?target=all&query=";
    String query;




    public double getIngredientPrice(Ingredient ingredient) {
        try {
            query = URLEncoder.encode(ingredient.getIngredientName(), "UTF-8");
        } catch (IOException e) {
            System.out.println("URL 변환 에러!!");
        }

//        System.out.println(URL+query);

        try {
            // 검색 결과 페이지 가져오기
            Document document = Jsoup.connect(URL+query)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36 Edg/105.0.1343.53").timeout(10000).get();

            // 상품 목록 element 가져오기
            Elements itemInfo = document.select("#divProductImg .cunit_info");
            int cnt = 0;
            double sum = 0;

            // 상위 8개 상품을 검사
            int num;
            if(itemInfo.size()<8){
                num = itemInfo.size();
            } else {
                num = 8;
            }
            for (int i=0; i<num; i++) {
                Element e = itemInfo.get(i);

                // title: 상품명
                String title = e.select(".title .tx_ko").text();

                // pricePart: (1g당:129원) 같은 파트
                String pricePart = "";
                pricePart = e.select(".cunit_prw .unit").text();
                boolean flag = true;

                // 블락된 단어가 들어가는 경우 flag를 false 처리
                String[] blockWords = ingredient.getIngredientBlockWord().split(" ");
                if(!blockWords[0].equals("")) {
                    for (String word : blockWords) {
                        if (title.contains(word)) {
                            flag = false;
                            break;
                        }
                    }
                }

                // 블락된 단어가 들어가거나, 단위 가격이 존재하지 않는 경우 SKIP
                if(!flag || pricePart.equals("")) {
                    continue;
                }

//                System.out.println(title);


                // 가격 부분에서 숫자만 추출
                String priceStr = pricePart.substring(pricePart.indexOf(":")+1, pricePart.indexOf("원"));
                Double price = Double.parseDouble(priceStr.replaceAll("[^0-9]", ""));

                // unitPart: 100g
                String unitPart = pricePart.substring(pricePart.indexOf("(")+1, pricePart.indexOf('당'));

                // unit: g
                String unit = unitPart.replaceAll("[0-9]","");
                // weight: 100
                int weight = Integer.parseInt(unitPart.replaceAll("[^0-9]", ""));

                if (!unit.equals(ingredient.getIngredientUnit())) {
                    if(unit.equals("kg")){
                        unit = "g";
                        weight*=1000;
                    } else if (unit.equals("개")){
                        unit = "g";
                        if(ingredient.getIngredientWeight()!=-1) {
                            price /= ingredient.getIngredientWeight();
                        } else {
                            System.out.println(ingredient.getIngredientId() +"번 재료 " + ingredient.getIngredientName() + "에서 문제 발생! ");
                        }
                    }
                }

                // 1g 당 가격으로 환산
                if(unit.equals("g")||unit.equals("ml")) {
                    price /= weight;
                    weight /= weight;
                    sum+=price;
                    cnt++;
                } else {
                    System.out.println("unitException!!!! in " + ingredient.getIngredientId() + "번 재료 " + ingredient.getIngredientName());
                }
            }

//            System.out.println(sum + ", "+ cnt);
            return Math.round((sum/(double)cnt)*10)/10.0;

        } catch (IOException e) {
            System.out.println("에러!!!");
            throw new RuntimeException(e);
        }
    }

}
