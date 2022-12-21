package packagedelevery;

import java.util.*;
import java.util.stream.Collectors;

// TODO: implement interface
public class Delivery implements Iterable<Package> {

	private final String address;
	private final Map<String, Set<Package>> packagesByAddress;
        private Map<String, Set<Package>> packagesBySortedAddress;
      private  Package prePackCall=null;
      private  int num=0;
      private int x=0;
      private boolean isCalled=false;
	public Delivery(String address, Map<String, Set<Package>> packages) {
		this.address = address;
		this.packagesByAddress = packages;
                packagesBySortedAddress=null;
	}

	public Delivery(String address) {
		this.address = address;
		this.packagesByAddress = new HashMap<>();
	}


	public String getAddress() {
		return address;
	}

	public void add(Package aPackage) {
        boolean flag=false;
          // looping over keys
        for (String key : packagesByAddress.keySet()) 
        {
             if(key.equals(aPackage.getAddress())){
                flag=true;
                Set<Package> pack = packagesByAddress.get(key);
                pack.add(aPackage);
                break;
            }
            
        }
       //for new stock
        if(!flag){
          packagesByAddress.put(aPackage.getAddress(),new HashSet<>());
           Set<Package> pack = packagesByAddress.get(aPackage.getAddress());
                pack.add(aPackage);
        }
       
        
	}
        private  void sortbykey()
    {
       packagesBySortedAddress
            = packagesByAddress.entrySet()
                  .stream()
                  .sorted((i1, i2)
                              -> i1.getKey().compareTo(
                                  i2.getKey()))
                  .collect(Collectors.toMap(
                      Map.Entry::getKey,
                      Map.Entry::getValue,
                      (e1, e2) -> e1, LinkedHashMap::new));
       
       
       
    }

	@Override
	public String toString() {
		return "Delivery:\n  Address: " + address;
	}

	// TODO: implement iterator


	public static void main(String[] args) {
		// TODO test your code:
		Delivery delivery = new Delivery("Deliveryplace 1");
		delivery.add(new Package("Iceavenue 5", "Penguinway 4", 100));
		delivery.add(new Package("Iceavenue 5", "Penguinway 1", 85));
		delivery.add(new Package("Iceavenue 5", "Penguinway 1", 73));
		delivery.add(new Package("Snowlane 3", "Antarcticplace 3", 107));
		delivery.add(new Package("Winterhighway 89", "Antarcticplace 27", 20));
		delivery.add(new Package("Penguinway 6", "Tierpark Hellabrunn, Tierparkstr. 30", 1));
		delivery.add(new Package("Tierpark Hellabrunn, Tierparkstr. 30", "Penguinway 6", 0.3));
		delivery.add(new Package("Antarcticplace 123", "Penguroad 1", 6));
                
//               Package p1= new Package("Antarcticplace 123", "Penguroad 1", 6);
//               Package p2=new Package("Winterhighway 89", "Antarcticplace 27", 20);
//		delivery.add(new Package("Iceavenue 5", "Penguinway 1", 5));
//		delivery.add(new Package("Iceavenue 5", "Penguinway 1", 0.5));
// 		delivery.add(new Package("Iceavenue 5", "Icevanue 1", 3));
               //System.out.println(p2.compareTo(p1));
           // System.out.println(" "+delivery.iterator().hasNext());
//          int x=2;

            System.out.println(delivery.iterator().next());
delivery.iterator().remove();
            while( delivery.iterator().hasNext() ) {
              
System.out.println(delivery.iterator().next() + " ") ;                    
}
                  System.out.println(delivery.iterator().next());

            //System.out.println(delivery.iterator().next());

//            System.out.println("\n\n----X");
     // delivery.iterator().remove();
    


	}

    @Override
    public Iterator<Package> iterator() {
            
sortbykey();
       Iterator<Package> iter = new Iterator<>() {
      @Override   
 public void remove(){
     if(prePackCall==null || x==1)
                         throw new NoSuchElementException();

       Package p= prePackCall;
                    String temp=p.getSender();
                    p.setSender(p.getAddress());
                    p.setAddress(temp);
                    add(p);
                     sortbykey();
                     Set<Package> pack = packagesBySortedAddress.get(p.getSender());
                 for(Package p1 : pack){
                    if(p.getAddress()==p1.getSender()){
                        prePackCall=p1;
                        break;
                    }
                 }
               x=1;
 };
 @Override
 public boolean hasNext() {
            for (String key : packagesBySortedAddress.keySet()) 
              {
                 Set<Package> pack = packagesBySortedAddress.get(key);
                 for(Package p : pack){
                    if(!p.isLoaded()){
                        return true;
                    }
                    }
               }
             return false;    
        }
            

            @Override
            public Package next() {
                 Package p=null;
                 if(num==packagesBySortedAddress.size() && isCalled && x==1){
                     isCalled=false;
                     x=0;
                        return prePackCall;
                        
                 }
                  
                if (!hasNext() ) {
                throw new NoSuchElementException();
            }
               
               else
                {
                  
                    for (String key : packagesBySortedAddress.keySet()) 
              {
                 Set<Package> pack = packagesBySortedAddress.get(key);
          
            if(pack.size()>1){
                 
             List<Package> arr = new ArrayList<>(pack);
             for (int i = 0; i < arr.size(); i++) {     
            for (int j = i+1; j < arr.size(); j++) {     
               if(arr.get(i).getWeight() < arr.get(j).getWeight()) {    
                    Package temp=arr.get(i);
                        arr.set(i,arr.get(j));
                        arr.set(j, temp);   
               }     
            }     
        }
                for (int i = 0; i < arr.size(); i++) {
                    if(!arr.get(i).isLoaded()){
                       arr.get(i).setLoaded(true);
                       if(!isCalled){
                         prePackCall=arr.get(i);
                          isCalled=true; 
                       }
                       num++;
                       return  arr.get(i);
                    }
                }
   
            }
            else{
                 for(Package p1 : pack){
                     
                    if(!p1.isLoaded() ){
                        p1.setLoaded(true);
                        p=p1;
                        if(!isCalled){
                         prePackCall=p;
                          isCalled=true; 
                       }
                        num++;
                      return p;
                    }
                    }
  
                  }
              }
               }
              
                
                return  p;
                }
            

           
        };
 
        return iter;
    }
  
   

   
}
