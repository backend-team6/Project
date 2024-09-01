import product.domain.Product;
import product.manager.DaisoManager;
import product.manager.DaisoManagerImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Daiso {
    DaisoManager manager;
    BufferedReader br;

    public static void main(String[] args) throws IOException {
        Daiso daiso=new Daiso();
        daiso.selectMenu();
    }

    private Daiso() {
        manager = DaisoManagerImpl.getInstance();
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    void selectMenu(){
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            Menu();
            System.out.print("메뉴 선택 >> ");
            int MenuNum;
            try {
                MenuNum = Integer.parseInt(br.readLine());
                switch (MenuNum) {
                    case 1:
                        readProductData();
                        break;
                    case 2:
                        getAllProduct();
                        break;
                    case 3:
                        searchByProductName();
                        break;
                    case 4:
                        getProductsByPrice();
                        break;
                    case 5:
                        updateProduct();
                        break;
                    case 6:
                        deleteProduct();
                        break;
                    case 9:
                        System.out.println("종료합니다.");
                        return;
                    default:
                        System.out.println("잘못 입력하셨습니다.");
                        break;

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    void Menu(){
        System.out.println("====메 뉴====");
        System.out.println("1. 상품 추가");
        System.out.println("2. 상품 전체 출력");
        System.out.println("3. 상품명으로 데이터 검색");
        System.out.println("4. 오름차순으로 데이터 출력");
        System.out.println("5. 상품 수정");
        System.out.println("6. 상품 삭제");
        System.out.println("9. 종료");
        System.out.println("=================");
    }

    void deleteProduct() throws IOException{
        System.out.print("삭제할 상품 코드 입력 : ");
        String pId = br.readLine();
        int chk=manager.deleteProduct(Integer.parseInt(pId));
        System.out.println(chk>=1?"삭제를 완료하였습니다. ":"삭제 실패하였습니다.");
    }

    void searchByProductName() throws IOException {
        System.out.print("검색 할 상품명 입력 : ");
        String name = br.readLine();
        Product product = manager.searchByProductName(name);
        if (product == null) {
            System.out.println("존재하는 제품이 없습니다.");
            return;
        }
        System.out.println(product);
    }

    void getProductsByPrice() {
        List<Product> products = manager.getProductsByPrice();
        for (Product p: products) {
            System.out.println(p);
        }
    }

    void updateProduct() throws IOException {
        System.out.print("수정할 상품 코드 입력 : ");
        int productId = Integer.parseInt(br.readLine());

        Map<String, String> changeData = new HashMap<>();
        System.out.println("------- 수정할 내용 입력 -------");
        System.out.println("종료하려면 0을 입력하세요");
        while (true) {
            System.out.print("수정할 컬럼명 : ");
            String changeColumn = br.readLine();
            if (changeColumn.equals("0")) break;
            System.out.println("수정할 내용");
            String changeContent = br.readLine();
            changeData.put(changeColumn, changeContent);
        }
        if(manager.updateProduct(productId, changeData) == changeData.size()) {
            System.out.println("수정이 완료되었습니다.");
        }
    }

    void getAllProduct() {
        List<Product> products = manager.getAllProduct();
        for(Product p:products){
            System.out.println(p);
        }
    }

    void readProductData() throws IOException {
        System.out.println("상품명을 입력해주세요.");
        String productName = br.readLine();

        System.out.println("상품 분류를 입력해주세요.");
        String category = br.readLine();

        System.out.println("상품 가격을 입력해주세요.");
        int price = Integer.parseInt(br.readLine());

        System.out.println("행사명을 입력해주세요.");
        String event = br.readLine();

        System.out.println("수량을 입력해주세요.");
        int stock = Integer.parseInt(br.readLine());

        System.out.println("입고날짜를 입력해주세요. ex) 2024-07-31");
        String date = br.readLine();

        Product product = new Product(productName, category, price, event, stock, Date.valueOf(date));

        int result = manager.addProduct(product);

        if (result > 0) {
            System.out.println("제품 생성 성공");
        } else
            System.out.println("제품 생성 실패");
    }
}