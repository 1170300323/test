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
							System.out.println("�����е�ĳЩ���ֲ���������");
							return false;
						}
						if(a[i].charAt(j) == ' ') {
							System.out.println("��������֮�䲢��ʹ��\'\\t\'�ָ�");
							return false;
						}
						if(!(a[i].charAt(j) >= '0' && a[i].charAt(j) <= '9')) {
							System.out.println("�ı��к��зǷ��ַ�");
							return false;
						}
					}
					b[t][i] = Integer.valueOf(a[i]);
				}
				t++;
				if(q != x) {
					System.out.println("�ı����ݱ�ʾ�Ĳ��Ǿ���");
					return false;
				}
				q = x;
			}
			if(t != x) {
				System.out.println("���������������");
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
			System.out.println("���벻��Ϊ����");
			return false;
		}
		if(n % 2 == 0) {
			System.out.println("���벻��Ϊż��");
			return false;
			
		}
		int magic[][] = new int[n][n];//�����ά����magic��nΪ����Ĵ�С
		int row = 0, col = n / 2, i, j, square = n * n;
		for (i = 1; i <= square; i++) {
			magic[row][col] = i;//���n������Ǹ������õ���colҲ����һ����������col��Ϊ������±겻���Ǹ���
			//����������ż��������һ������֮��row��Խ�磬�����������
			//���������˹���magicsquare�ļ���
			/*
			  *һ�����������룺���� 1 �����������м�ĸ����У�
			  *����б����Ī���������Ͻ�б�У������������֣�
			  *�ϳ���ʱ���·ţ�������Ϸ�������ϱ߽磬���Գ��������ⷽ��λ��Ϊ��׼����������ֱ���������ж�Ӧ�ĸ����У�
			  *�ҳ���ʱ����ţ�ͬ�ϣ����ҳ��˱߽磬���Գ��������ⷽ��λ��Ϊ��׼��������ƽ���������ж�Ӧ�ĸ����У�
			  *���ر����¸������������ϵĸ����ѱ���������ռ�죬�ͽ� ��д������ĸ����У�
			  *��������һ��������������Ͻǳ��磬�������ظ��������ͬ������ 
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
