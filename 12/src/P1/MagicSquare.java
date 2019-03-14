package P1;
import java.io.*;

public class MagicSquare {
	static boolean isLegalMagicSquare(String fileName) {
		File file = new File(fileName);
		try {
			if(!file.exists()) {
				System.out.println("File does not exist");
				System.exit(-1);
			} 
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String temp;
			int n = 1000;
			int[][] b = new int[n][n];
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					b[i][j] = 0;
				}
			}
			int t = 0, x = 0, q = 0;
			while((temp = reader.readLine()) != null) {
				String[] a = temp.split("\t");
				x = a.length;
				if(t == 0)
					q = x;
				for(int i = 0; i < x; i++) {
					int y = a[i].length();
					for(int j = 0; j < y; j++) {
						if(a[i].charAt(j) == '-' || a[i].charAt(j) == '.') {
							System.out.println("矩阵中的某些数字并非正整数");
							return false;
						}
						if(a[i].charAt(j) == ' ') {
							System.out.println("矩阵数字之间并非使用\'\\t\'分割");
							return false;
						}
						if(!(a[i].charAt(j) >= '0' && a[i].charAt(j) <= '9')) {
							System.out.println("文本中含有非法字符");
							return false;
						}
					}
					b[t][i] = Integer.valueOf(a[i]);
				}
				t++;
				if(q != x) {
					System.out.println("文本数据表示的并非矩阵");
					return false;
				}
				q = x;
			}
			if(t != x) {
				System.out.println("矩阵行列数不相等");
				return false;
			}
			int sum = 0, s = 0;
			for(int i = 0; i < x; i++) {
				for(int j = 0; j < x; j++) {
					s += b[i][j];
				}
				if(i == 0)
					sum = s;
				if(sum != s)
					return false;
				s = 0;
			}
			for(int j = 0; j < x; j++) {
				for(int i = 0; i < x; i++) {
					s += b[i][j];
				}
				if(sum != s)
					return false;
				s = 0;
			}
			for(int i = 0; i < x; i++) {
				s += b[i][i];
			}
			if(sum != s)
				return false;
			s = 0;
			int j = 1;
			for(int i = 0; i < x; i++) {
				s += b[x - j][j - 1];
				j++;
			}
			if(sum != s)
				return false;
		}catch(IOException e){
			e.printStackTrace();
		}
		return true;
	}
	public static boolean generateMagicSquare(int n) {
		if(n < 0) {
			System.out.println("输入不能为负数");
			return false;
		}
		if(n % 2 == 0) {
			System.out.println("输入不能为偶数");
			return false;
			
		}
		int magic[][] = new int[n][n];//定义二维数组magic，n为方阵的大小
		int row = 0, col = n / 2, i, j, square = n * n;
		for (i = 1; i <= square; i++) {
			magic[row][col] = i;//如果n输入的是负数，得到的col也会是一个负数，而col作为数组的下标不能是负数
			//如果输入的是偶数，经过一定运算之后row会越界，导致数组溢出
			//这里运用了构造magicsquare的技巧
			/*
			  *一居上行正中央：数字 1 放在首行最中间的格子中；
			  *依次斜填切莫忘：向右上角斜行，依次填入数字；
			  *上出框时向下放：如果右上方向出了上边界，就以出框后的虚拟方格位置为基准，将数字竖直降落至底行对应的格子中；
			  *右出框时向左放：同上，向右出了边界，就以出框后的虚拟方格位置为基准，将数字平移至最左列对应的格子中；
			  *排重便在下格填：如果数字右上的格子已被其它数字占领，就将 填写在下面的格子中；
			  *右上排重一个样：如果朝右上角出界，和上面重复的情况做同样处理 
			 */
			if (i % n == 0)
				row++;
			else {
				if (row == 0)
					row = n - 1;
				else
					row--;
				if (col == (n - 1))
					col = 0;
				else
					col++;
			}
		}
		File fl = new File("src\\P1\\txt\\6.txt");
		try {
			if(!fl.exists()) {
				fl.createNewFile();
			}
			FileWriter fw = new FileWriter(fl);
			fw.write("");
			for (i = 0; i < n; i++) {
				for (j = 0; j < n; j++) {
					fw.write(magic[i][j] + "\t");
				}
				fw.write("\n");
			}
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	public static void main(String[] args) {
		System.out.println(isLegalMagicSquare("src\\P1\\txt\\1.txt"));
		System.out.println(isLegalMagicSquare("src\\P1\\txt\\2.txt"));
		System.out.println(isLegalMagicSquare("src\\P1\\txt\\3.txt"));
		System.out.println(isLegalMagicSquare("src\\P1\\txt\\4.txt"));
		System.out.println(isLegalMagicSquare("src\\P1\\txt\\5.txt"));
		if(generateMagicSquare(6))
			System.out.println(isLegalMagicSquare("src\\P1\\txt\\6.txt"));
	}
}
