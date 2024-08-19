package daiso;

import java.util.List;
import java.util.Map;

public interface DaisoManeger {
    int addProduct(Product p);

    List<Product> getAllProduct();

    Product searchByProductName(String name);

    List<Product> getProductsByPrice(); // 오름차순

    int updateProduct(int productId, Map<String, String> changeData);
    // 1차로 productId
    // Map<바꿀 컬럼, 바꿀 내용> ex) '상품명', '핸드폰'

    int deleteProduct(int productId); //return -> 성공 여부
}
