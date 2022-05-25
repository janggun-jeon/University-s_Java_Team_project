package realtimeCMS;
import java.util.*;
//��� �������: hashmap("��ǰ�̸�", "�ֹ���ü ���۷���")
class Define {//c����� #define�� ���� ����. ���� ����� �⺻ �����ð� 1, �ƴ� ����� 2�� ����
    public static final int DefaultTime =2;
    public static final int RoketDefaultTime =1;
}
class PrepareData { //�⺻ ������ �Ӽ�. �����ð��� �ֹ��� ����.
    int relatedtime;
    Order order;
}


class Prepare {
    private static Queue<PrepareData> tem = new LinkedList<PrepareData>();//ó���� ������ �ֹ����� �ֱ� ���� �ӽ� ť ����
    PrepareData element;//�⺻ ������ �Ӽ� 
    Prepare(Order order){//��ü ������ ���ÿ� ��ü�� �����ð� ���, �����ð� ��� ���: �����ð� =  �⺻ �����ð� 1+ ����/3 +����/5
        element=new PrepareData();
        this.element.order = order;
        this.element.relatedtime= (order.rocketDelivery? Define.RoketDefaultTime : Define.DefaultTime) + (int)order.totalvolume/5;
        tem.add(this.element);
    }
    public void SwitchState(){//ť�� �������� �з��۾��� ������� �ֹ��� �ѱ��. ���� ���߿� ó�� �뷮(4)�� �����Ǹ� �ݺ����� �����ϰ� ������ �ֹ��� �״�� ť�� ���� �ȴ�.
        while(!tem.isEmpty()){
            if(!Classification.AddOrder(tem.remove()))
                break;          
        }
        //�� ������ ���� ���� ���°� ����� ��ǰ ����� print�ϴ� �Լ��� ������ ��ȹ
    }
}

class Classification{//�з��۾� Ŭ����ee
    
    private static ArrayList<PrepareData> ClassifyingOrder = new ArrayList<PrepareData>(4);//arraylist�� ���� �з��۾����� �ֹ����� �ִ´�.
    private static ArrayList<Order> ReturnOrder = new ArrayList<Order>();

    public static boolean AddOrder(PrepareData delayorder){//����Ʈ�� �뷮�� �ʰ����� �ʴ´ٸ� �����͸� �ְ� ������ ��ȯ�Ѵ�.
        if(ClassifyingOrder.size()!=4){
            ClassifyingOrder.add(delayorder);
            return true;
        }
        else
           return false;
    }
    
    public static ArrayList<Order> CompleteClassfication(){//����Ʈ���� �ϳ��� ���� �����ð��� -1�� �� ��, �����ð��� 0�̸� ��ȯ�� ����Ʈ�� �ְ� �ƴϸ� �ٽ� �з��۾� ����Ʈ�� �ִ´�. ��� �ֹ���
                                                           //�� ���� ����Ʈ�� �Ѳ����� ��ȯ�Ѵ�. ���ο��� �̸� ��� ������� ���� �� �𸣰ھ �ϴ��� �̷��� ��������ϴ�.
        int index=0;
        PrepareData temp; 
        if(ReturnOrder.size()!=0)
        ReturnOrder.clear();
        while(index<=ClassifyingOrder.size()){
            temp=ClassifyingOrder.remove(index);
            if(temp.relatedtime--==0) 
            ReturnOrder.add(temp.order);
            else{
                ClassifyingOrder.add(index,temp);
            }
            index++;
            
        }
        return ReturnOrder;

    }
    }
    //���� �ʿ��� �κ� �� �߰��� ����...�ǰ��ؼ� �� �̻� �ڵ��� ����...