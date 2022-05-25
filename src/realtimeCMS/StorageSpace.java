package realtimeCMS; // Real-Time Courier Monitoring System
import java.lang.String;
import java.util.*;

class product { // 상품정보
    String productName; // 상품명
    int quantity; // 재고수량
    double volume; // 상품무게[kg], 1개당
    public product(String productName, int quantity, double volume) {
        this.productName = productName;
        this.quantity = quantity;
        this.volume = volume; 
    }
}

class StorageSpace { // 창고공간 
    
    HashMap<Integer, product> inventory = new HashMap<Integer, product>(); // 재고를 고유식별번호[=key]로 관리할 자료구조
    LinkedList<product> queue = new LinkedList<product>(); // 발주할 product들의 대기열
    public StorageSpace() {
        inventory.put(1, new product("[표백제]", 10, 2));
        inventory.put(2, new product("[주방세제]", 10, 1));
        inventory.put(3, new product("[골프가방]", 10, 151.7));
        inventory.put(4, new product("[맥북]", 10, 1.2));
        inventory.put(5, new product("[드럼세탁기]", 10, 520));
        inventory.put(6, new product("[선풍기]", 10, 97.8));
        inventory.put(7, new product("[무선 진공청소기]", 10, 2.9));
        inventory.put(8, new product("[로봇청소기]", 10, 73.3));
        inventory.put(9, new product("[김치냉장고]", 10, 875));
        inventory.put(10, new product("[일반세탁기]", 10, 515.3));
        inventory.put(11, new product("[무선마우스]", 10, 0.2));
        inventory.put(12, new product("[건식 다리미]", 10, 4.8));
        inventory.put(13, new product("[무게조절 덤벨세트]", 10, 20.3));
        inventory.put(14, new product("[케틀벨]", 10, 21.9));
        inventory.put(15, new product("[이불]", 10, 3));
        inventory.put(16, new product("[5단 서랍장]", 10, 25.5));
        inventory.put(17, new product("[매트리스]", 10, 700));
        inventory.put(18, new product("[메뚜기쌀]", 10, 02));
        inventory.put(19, new product("[냉동 대패삼겹살]", 10, 1));
        inventory.put(20, new product("[닭볶음탕용 닭고기]", 10, 2));
        inventory.put(21, new product("[짐볼]", 10, 150.6));
        inventory.put(22, new product("[맥심 커피믹스 대용량]", 10, 1.95));
        inventory.put(23, new product("[소고기죽]", 10, 0.45));
        inventory.put(24, new product("[여행용 백팩]", 10, 45));
        inventory.put(25, new product("[사양벌꿀]", 10, 2));
        inventory.put(26, new product("[흑당시럽]", 10, 2.5));
        inventory.put(27, new product("[밀피터리 백팩]", 10, 38.35));
        inventory.put(28, new product("[방울토마토 1봉지]", 10, 1));
        inventory.put(29, new product("[키위 1봉지]", 10, 1.5));
        inventory.put(30, new product("[냉동 블루베리 1봉지]", 10, 1));
        inventory.put(31, new product("[오렌지 1봉지]", 10, 1.3));
        inventory.put(32, new product("[수박 1통]", 10, 7));
        inventory.put(33, new product("[참외 1박스]", 10, 5));
        inventory.put(34, new product("[레몬 1봉지]", 10, 1.2));
        inventory.put(35, new product("[사과 1봉지]", 10, 2.5));
        inventory.put(36, new product("[포도 1봉지]", 10, 1.8));
        inventory.put(37, new product("[캐리어]", 10, 107.15));
        inventory.put(38, new product("[냉동 왕교자만두]", 10, 1.4));
        inventory.put(39, new product("[배추김치]", 10, 1.8));
        inventory.put(40, new product("[냉동 치킨너켓 1봉지", 10, 1.2));
        inventory.put(51, new product("[고양이 사료 1박스]", 10, 3));
        inventory.put(41, new product("[클렌징폼]", 10, 0.25));
        inventory.put(42, new product("[비누 10묶음]", 10, 0.9));
        inventory.put(43, new product("[탈모샴푸]", 10, 1));
        inventory.put(44, new product("[컬크림]", 10, 0.5));
        inventory.put(45, new product("[대용량 탈색제 1박스]", 10, 3));
        inventory.put(46, new product("[치약]", 10, 0.1));
        inventory.put(47, new product("[다우니 섬유유연제 대용량]", 10, 8.5));
        inventory.put(48, new product("[A4용지 100매]", 10, 0.8));
        inventory.put(49, new product("[호텔수건 10묶음]", 10, 2));
        inventory.put(50, new product("[특란 30구]", 10, 1.8));
        inventory.put(51, new product("[왕계란 30구]", 10, 2.1));
        inventory.put(52, new product("[생유산균 어린이용]", 10, 0.15));
        inventory.put(53, new product("[냉동 닭가슴살 1봉지]", 10, 2));
        inventory.put(54, new product("[참치통조림 12팩]", 10, 1.2));
        inventory.put(55, new product("[비타민 1통]", 10, 0.15));
        inventory.put(56, new product("[기저귀 10묶음]", 10, 1.45));
        inventory.put(57, new product("[수딩 파우더]", 10, 0.05));
        inventory.put(58, new product("[무선 전동 드라이버]", 10, 0.4));
        inventory.put(59, new product("[디퓨저]", 10, 0.2));
        inventory.put(60, new product("[모기스프레이]", 10, 0.05));        
        inventory.put(61, new product("[건조대]", 10, 1));
        inventory.put(62, new product("[샴푸]", 10, 1));
        inventory.put(63, new product("[욕실세정제]]", 10, 0.55));
        inventory.put(64, new product("[액체세제]", 10, 3.4));
        inventory.put(65, new product("[휴지통]]", 10, 9));
        inventory.put(66, new product("[올리브유]", 10, 1));
        inventory.put(67, new product("[설탕 1포대]", 10, 1.5));
        inventory.put(68, new product("[텀블러]", 10, 2));
        inventory.put(69, new product("[베이킹소다 1봉지]", 10, 1.62));
        inventory.put(70, new product("[참기름 1병]", 10, 0.35));        
        inventory.put(71, new product("[고춧가루 1병]", 10, 0.15));
        inventory.put(72, new product("[다진마늘 1봉]", 10, 0.5));
        inventory.put(73, new product("[실내 감시카메라]", 10, 1));
        inventory.put(74, new product("[노트북 거치대]", 10, 1.6));
        inventory.put(75, new product("[게이밍 장패드]", 10, 0.55));
        inventory.put(76, new product("[전자체중계]", 10, 1.55));
        inventory.put(77, new product("[갤럭시워치]", 10, 0.5));
        inventory.put(78, new product("[모짜렐라 치즈 1봉지]", 10, 1));
        inventory.put(79, new product("[휘핑크림 1통]", 10, 1));
        inventory.put(80, new product("[4k UHD TV 43인치]", 10, 138.2));       
        inventory.put(81, new product("[양문형 냉장고]", 10, 846));
        inventory.put(82, new product("[미니냉장고]", 10, 24));
        inventory.put(83, new product("[무선 키보드 마우스 세트]", 10, 2));
        inventory.put(84, new product("[공기청정 필터]", 10, 7));
        inventory.put(85, new product("[호두 1병]", 10, 1));
        inventory.put(86, new product("[땅콩 1봉지]", 10, 0.5));
        inventory.put(87, new product("[대용량 빨래가방]", 10, 45));
        inventory.put(88, new product("[분리수거함]", 10, 50));
        inventory.put(89, new product("[살균소독제]", 10, 1));
        inventory.put(90, new product("[배수구 세정제]", 10, 2));
        inventory.put(91, new product("[압력밥솥]", 10, 33.85));
        inventory.put(92, new product("[바디워시]", 10, 1.05));
        inventory.put(93, new product("[헤드셋]", 10, 3.4));
        inventory.put(94, new product("[자바 입문서]", 10, 0.8));
        inventory.put(95, new product("[C언어 입문서]", 10, 0.5));
        inventory.put(96, new product("[C++ 입문서]", 10, 0.45));
        inventory.put(97, new product("[파이썬 입문서]", 10, 0.6));
        inventory.put(98, new product("[자바스크립트 입문서]", 10, 0.35));
        inventory.put(99, new product("[웹프로그래밍 입문서]", 10, 0.6));
        inventory.put(100, new product("[자료구조 입문서]", 10, 0.8));
    }
    public LinkedList<product> request() { // 발주
        return queue; // 납품공간에 발주할 상품정보의 리스트를 반환
    }
    public boolean shipment(Order ord) { // 출하
        if (inventory.get(ord.key).quantity > ord.quantity) { // 재고수량 >= 주문수량
            inventory.get(ord.key).quantity -= ord.quantity; // 재고 삭감
            ord.nextState(); // 출하됨 출력
            if (inventory.get(ord.key).quantity == 0) { // 재고가 0
                queue.add(inventory.get(ord.key)); // 발주 대기열에 상품정보를 추가
            }
            return true; // 출하성공
        }
        else { // 재고부족
            ord.sold_out(); // 품절됨 출력
            queue.add(inventory.get(ord.key));
            return false; // 출하실패
        }
    }
}