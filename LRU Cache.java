/*
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

Solution: copied from 
https://sites.google.com/site/codingbughunter/algorithm-code-questions/leetcode-questions/leetcode-lru-cache-java
The idea is avoiding the missing link between hash table value and indexing into a normal heap by hashing into a node address,
then manually maintaining the sorting order in the double linked list. This utilizes the fact that each read on the double
linked list guarantee the node will move to the head. So no further comparasion is necessary, hence heap isn't required.

 * */
import java.util.HashMap;

public class LRUCache {

	public HashMap<Integer, LRUCache.DoubleLinkedListNode> table = new HashMap<Integer, LRUCache.DoubleLinkedListNode>();
	public DoubleLinkedListNode head;
	public DoubleLinkedListNode end;
	public int capacity;
	public int len;

	public LRUCache(int capacity) {
		this.capacity = capacity;
		len = 0;
	}

	public int get(int key) {
		if (table.containsKey(key)) {
			removeNode(table.get(key));
			setHead(table.get(key));
			return table.get(key).val;
		} else {
			return -1;
		}
	}
	
	public void removeNode(DoubleLinkedListNode node){
		DoubleLinkedListNode cur = node;
		DoubleLinkedListNode pre = cur.pre;
        DoubleLinkedListNode post = cur.post;
        if(pre!=null){
            pre.post = post;
        }else{
        	head = post;
        }
        if(post != null){
            post.pre = pre;
        }else{
        	end = pre;
        }
	}
	
	public void setHead(DoubleLinkedListNode node){
		node.post = head;
		node.pre = null;
		if(head != null){
			head.pre = node;
		}
		head = node;
		if(end == null){
			end = node;
		}
	}

	public void set(int key, int value) {
        if(table.containsKey(key)){
            DoubleLinkedListNode cur = table.get(key);
            cur.val = value;
            removeNode(cur);         
            setHead(cur);
        }else{
            DoubleLinkedListNode cur = new DoubleLinkedListNode(key,value);
            if(len < capacity){
                setHead(cur);
                table.put(key,cur);
                len++;
            }else{
            	table.remove(end.key);
                end = end.pre;
                if(end != null){
                	end.post = null;
                }
                
                setHead(cur);
                table.put(key,cur);
            }
        }
    }

	public class DoubleLinkedListNode {
		public int val;
		public int key;
		public DoubleLinkedListNode pre;
		public DoubleLinkedListNode post;

		public DoubleLinkedListNode(int key, int value) {
			val = value;
			this.key = key;
		}

	}
}
