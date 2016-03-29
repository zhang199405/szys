package jddszys;
import java.util.ArrayList;
import java.util.Random;  
  
  
class Four   
{  
      
    private TreeNodes root;  
    private int num;  
    private ArrayList<TreeNodes> opeList = new ArrayList<TreeNodes>();  
      
    public Four(int num){  
        this.num = num;  
    }  
      
    public int getNum(){  
        return num;  
    }  
      
    public void setNum(int num){  
        this.num = num;  
    }  
      
    public void setTreeNode(TreeNodes root){  
        this.root = root;  
    }  
      
      
    /** 
     * ��ȡ���յı��ʽ��������CalAndVal()��������� 
     *  
     * @return str 
     */  
    public String toString(){  
        String str = root.toString();  
        str = str.substring(1, str.length()-1);  
        return str;  
    }  
      
    /** 
     * ���㲢��֤���ʽ 
     *  
     * @return result 
     */  
    public String CalAndVal(){  
        return root.getResult();  
    }  
      
    /** 
     * ��������������(����)  
     *  
     * @return deep 
     */  
    public int getDeep(){  
        int i = this.num;  
        int deep = 2;  
        while(i/2 > 0){  
            deep++;  
            i /= 2;  
        }  
        return deep;  
    }  
      
    /** 
     * ���ɶ����� 
     *  
     */  
    public void createBTree(){  
        TreeNodes lchild, rchild, lnode, rnode;  
          
        if(num == 1){  
            lchild = new TreeNodes(String.valueOf(Ran.getNumber(10)), null, null);  
            rchild = new TreeNodes(String.valueOf(Ran.getNumber(10)), null, null);  
            root = new TreeNodes(String.valueOf(Ran.getOperator()), lchild, rchild);  
        }  
        else{  
            int num1 = 0;  
            int n = getDeep() - 3;  
            boolean[] place = Ran.getChildPlace(num);  
            root = new TreeNodes(String.valueOf(Ran.getOperator()), null, null);  
            opeList.add(root);  
              
            for(int i = 0; i < n; i++){  
                for(int j = 0; j < (int)Math.pow(2, i); j++, num1++){  
                    lchild = new TreeNodes(String.valueOf(Ran.getOperator()), null, null);  
                    rchild = new TreeNodes(String.valueOf(Ran.getOperator()), null, null);  
                    opeList.get(j + num1).setChild(lchild, rchild);  
                    opeList.add(lchild);  
                    opeList.add(rchild);  
                }  
            }  
              
            for(int i = 0; i < place.length; i++){  
                if(place[i]){  
                    lnode  = new TreeNodes(String.valueOf(Ran.getNumber(10)), null, null);  
                    rnode  = new TreeNodes(String.valueOf(Ran.getNumber(10)), null, null);  
                    if(i%2 == 0){  
                        lchild = new TreeNodes(String.valueOf(Ran.getOperator()), lnode, rnode);  
                        opeList.add(lchild);  
                        opeList.get(num1).setLchild(lchild);  
                    }  
                    else{  
                        rchild = new TreeNodes(String.valueOf(Ran.getOperator()), lnode, rnode);  
                        opeList.add(rchild);  
                        opeList.get(num1).setRchild(rchild);  
                    }  
                }  
                else{  
                    if(i%2 == 0){  
                        lchild = new TreeNodes(String.valueOf(Ran.getNumber(10)), null, null);  
                        opeList.get(num1).setLchild(lchild);  
                    }  
                    else{  
                          
                        rchild = new TreeNodes(String.valueOf(Ran.getNumber(10)), null, null);  
                        opeList.get(num1).setRchild(rchild);  
                    }  
                }  
                num1 = num1 + i%2;  
            }  
        }  
    }  
}  
class Ran {  
      
    /** 
     * ��ȡ����ķ��� 
     *  
     * @return operator 
     */  
      
    public static char getOperator(){  
        char operator = 0;  
        Random ran = new Random();  
        int i = ran.nextInt(4);  
        switch(i){  
            case 0:  
                operator = '+';  
                break;  
            case 1:  
                operator = '-';  
                break;  
            case 2:  
                operator = '*';  
                break;  
            case 3:  
                operator = '/';  
                break;  
        }  
        return operator;  
    }  
      
      
    /** 
     * ��������ķ�Χ��ȡ����� 
     *  
     * @param max 
     * @return number 
     */  
      
    public static int getNumber(int max){  
        int number = 0;  
        Random ran = new Random();  
        number = ran.nextInt(max+1);  
        return number;  
    }  
      
    /** 
     * ����������ĸ�����������ӽڵ��λ�� 
     *  
     * @param num 
     * @return childPlace 
     */  
      
    public static boolean[] getChildPlace(int num){  
        int d = 0;  
        int size = 0, j=1;  
        while(num >= (int)Math.pow(2, j)){  
            j++;  
        }  
        d = (int)Math.pow(2, j) - 1 - num;  
        size = (int)Math.pow(2, j-1);  
        boolean[] k = new boolean[size];  
        for(int i = 0; i < size; i++){  
            k[i] = true;  
        }  
        for(int i = 0; i < d; i++){  
            Random ran = new Random();  
            int f = ran.nextInt(size);  
            while(k[f] == false)  
            {  
                f = ran.nextInt(size);  
            }  
            k[f] = false;  
        }  
        return k;  
    }  
}  
class TreeNodes {  
      
    private String str;  
    private TreeNodes rchild = null;  
    private TreeNodes lchild = null;  
      
    public TreeNodes(String str){  
        this.str = str;  
    }  
      
    public TreeNodes(String str, TreeNodes lchild, TreeNodes rchild){  
        this.str = str;  
        this.rchild = rchild;  
        this.lchild = lchild;  
    }  
      
    public void setChild(TreeNodes lchild, TreeNodes rchild){  
        this.lchild = lchild;  
        this.rchild = rchild;  
    }  
      
    public TreeNodes getRchild() {    
        return rchild;    
    }    
    public void setRchild(TreeNodes rchild) {    
        this.rchild = rchild;    
    }    
    public TreeNodes getLchild() {    
        return lchild;    
    }    
    public void setLchild(TreeNodes lchild) {    
        this.lchild = lchild;    
    }  
      
    public String getStr(){  
        return str;  
    }  
      
    /** 
     * ��ȡÿ���ڵ������������������� 
     * 1)����Ϊ0 
     * 2)�������� 
     * ����������������Ļ����������ת����������������� 
     *  
     * @return result 
     */  
    public String getResult(){  
        if(hasChild()){  
            switch(str){  
                case "+":  
                    return String.valueOf(Integer.parseInt(getLchild().getResult()) + Integer.parseInt(getRchild().getResult()));  
                case "-":  
                    return String.valueOf(Integer.parseInt(getLchild().getResult()) - Integer.parseInt(getRchild().getResult()));  
                case "*":  
                    return String.valueOf(Integer.parseInt(getLchild().getResult()) * Integer.parseInt(getRchild().getResult()));  
                case "/":  
                    if(getRchild().getResult().equals("0")){  
                        while(str.equals("/")){  
                            str = String.valueOf(Ran.getOperator());  
                        }  
                        return this.getResult();  
                    }  
                    else if(Integer.parseInt(getLchild().getResult()) % Integer.parseInt(getRchild().getResult()) != 0){  
                        while(str.equals("/")){  
                            str = String.valueOf(Ran.getOperator());  
                        }  
                        return this.getResult();  
                    }  
                    else  
                        return String.valueOf(Integer.parseInt(getLchild().getResult()) / Integer.parseInt(getRchild().getResult()));  
            }  
        }  
        return str;  
    }       
      
    /** 
     * �ȶ�ÿ������ʽ������ţ�Ȼ�����ȥ���ŷ���ȥ���������ʽ������ 
     *  
     * @return string 
     */  
    public String toString(){  
        String Lstr = "", Rstr = "", Str = "";  
        if(hasChild()){  
            //����������к��ӣ�˵����������һ�����ʽ�����������ֽڵ㡣  
            if(getRchild().hasChild()){                           
                //�ж��������ŵ�������Ƿ�Ϊ'/'  
                if(str.equals("/")){  
                    //��ȡ�������ı��ʽ����������  
                    Rstr = getRchild().toString();                
                }  
                //�ж��������ŵ�������Ƿ�Ϊ'*'��'-'  
                else if(str.equals("*") || str.equals("-")){  
                    //�ж�op�Ƿ�Ϊ'+'��'-'  
                    if(getRchild().str.equals("+") || getRchild().str.equals("-")){   
                        Rstr = getRchild().toString();            
                    }  
                    else{  
                        //��ȡ�������ı��ʽ������ȥ����   
                        Rstr = getRchild().toString().substring(1, getRchild().toString().length()-1);    
                    }  
                }  
                else{  
                    //����������֮�ⶼ�ǿ���ȥ���ŵġ�  
                    Rstr = getRchild().toString().substring(1, getRchild().toString().length()-1);        
                }  
            }  
            else{  
                Rstr = getRchild().str;  
            }  
            //�����������ͬ����������  
            if(getLchild().hasChild()){                                               
                if(str.equals("*") || str.equals("/")){  
                    if(getLchild().str.equals("+") || getLchild().str.equals("-")){  
                        Lstr = getLchild().toString();  
                    }  
                    else{  
                        Lstr = getLchild().toString().substring(1, getLchild().toString().length()-1);  
                    }  
                }  
                else{  
                    Lstr = getLchild().toString().substring(1, getLchild().toString().length()-1);  
                }  
            }  
            else{  
                Lstr = getLchild().str;  
            }  
            //��ȡ��ǰ������ʽ������������  
            Str = "(" + Lstr + str + Rstr + ")";                                      
        }  
        else{  
            //��û�к��ӣ�˵�������ֽڵ㣬ֱ�ӷ�������  
            Str = str;  
        }  
        return Str;  
    }  
      
    public boolean hasChild(){  
        if(lchild == null && rchild == null)  
            return false;  
        else  
            return true;  
    }  
}  
public class asd {  
    public static void main(String args[]){  
        Four bTree;  
        for(int i = 0; i < 6; i++){  
            bTree = new Four(3);  
            bTree.createBTree();  
            String result = bTree.CalAndVal();  
            System.out.println(bTree.toString() + "=" + result);  
        }  
    }  
}  