import java.util.*;

public class Main {
    public static void main(String[] args) {

        Random r = new Random();
        int ans = (int)r.nextInt(2);
//        System.out.println(ans); // when run multiple times, ans will produce different result. 2 is maximum number of
        // possible int vals, starting at 0 so bound of 2 -> 0,1 possibilities
        ans = r.nextInt(10); // changd the bound
        System.out.println(ans);
    }
}

//solution
class RandomizedSet {
    Map<Integer, Integer> dict;
    List<Integer> list;
    Random rand = new Random();

    public RandomizedSet() { // construct
        dict = new HashMap();
        list = new ArrayList();
    }

    public boolean insert(int val) {
        if (dict.containsKey(val)) return false;

        dict.put(val, list.size()); // val points to index
        list.add(list.size(), val); // index points to val
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) { // this is the tricky part because it we were only adding values, could just
        // add items to list only and and call a random index of a map only and call a random key within bound of map.size
        // but removing means looking an item up by val, which can't be done with just an index or just a key
        if (! dict.containsKey(val)) return false;

        // move the last element to the place idx of the element to delete
        int lastElement = list.get(list.size() - 1); // aquire last elemeent in list
        int idx = dict.get(val); // get index of val to be removed
        list.set(idx, lastElement); // redfine that list value at prior index
        dict.put(lastElement, idx); // redefn the index val of the last eelement
        list.remove(list.size() - 1); // last elemeeent current exists at current indeex and at index of deleted prior val
        // must delete duplicate at the end
        dict.remove(val); // remove key of value from map
        // net result is final list index/val removed, index of deleted val redeefined and val removed as key. sizee reduceed
        // without any gaps in indices which will be used to find a random val
        return true;
    }

    public int getRandom() {
        return list.get(rand.nextInt(list.size())); // random index in list returns associated value
    }
}


// right first steps but need one more supporting data structure
//class RandomizedSet {
//    HashMap m; // map stores key and val
//    Random random; // used to generate random int 'rInt'
//    int rInt;
//    int keyCount; // used to modify bound of Random
//
//    public RandomizedSet() {
//        this.m = new HashMap<>();
//        this.random = new Random();
//        this.rInt = 0;
//        this.keyCount = 0;
//    }
//
//    public boolean insert(int val) {
//        if(!m.containsKey(val)){
//            m.put(keyCount,val);
//            keyCount++;
//            return true;
//        }
//        return false;
//    }
//
//    public boolean remove(int val) {
//        // how to remove based on val unless key and val are same?
//        // can't input val as key eitheer because if we use random to get a number, and have a bpund of 10 which
//        // are sequential numbers, the vals could be 0 and 10000 as exampl, non-sequential
//
//    }
//
//    public int getRandom() {
//
//    }
//}