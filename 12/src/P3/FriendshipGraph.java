package P3;

import java.util.Queue;
import java.util.LinkedList;

public class FriendshipGraph {
	private int[][] edge = new int[1000][1000];
	private Person[] vertex = new Person[1000];
	int k = 0;
	
	/**
	  * 构造函数，用于初始化
	 */
	public FriendshipGraph() {
		for(int i = 0; i < 1000; i++) {
			for(int j = 0; j < 1000; j++) {
				this.edge[i][j] = 0;
			}
		}
	}
	
	/**
	  * 向图中添加顶点
	 */
	public boolean addVertex(Person p){
		for(int i = 0; i < k; i++) {
			if(vertex[i].getName() == p.getName()) {
				System.out.println("不能输入重复的人");
				return false;
			}
		}
		this.vertex[k] = p;
		p.id = k;
		k++;
		return true;
	}
	
	/**
	  * 向图中添加边
	 */
	public boolean addEdge(Person p1, Person p2) {
		if(p1.id == -1 || p2.id == -1) {
			System.out.println("不能与不存在的人建立关系");
			return false;
		}
		this.edge[p1.id][p2.id] = 1;
		return true;
	}
	
	/**
	  * 求两个人之间的最短距离
	 */
	public int getDistance(Person p1, Person p2) {
		int num = 0;
		boolean[] vis = new boolean[1000];
		while(vertex[num] != null) {
			num++;
		}
		for(int i = 0; i < num; i++) {
			vis[i] = false;
		}
		Queue<Person> queue = new LinkedList<Person>();
		p1.dep = 0;
		queue.add(p1);
		while(queue.peek() != null) {
			Person q = queue.remove();
			vis[q.id] = true;
			for(int j = 0; j < num; j++) {
				if(!vis[j] && edge[q.id][j] != 0) {
					queue.add(vertex[j]);
					vertex[j].dep = q.dep + 1;
				}
			}
		}
		return p2.dep;
	}
}
