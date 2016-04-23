package pingpang;

import java.util.Scanner;

public class Test1 {

	public static void main(String args[]) {
		int team_Num;//队伍的数量
		int team_Arr[];//队伍数组
		int team_temp[];
		boolean empty = false;//是否有轮空
		int jump;//调动幅度
		int round;//比赛轮数
		int flag;//标志，队伍的最大的，或者0，其他队伍在移动的时候，如果碰到他，将跳过
		int tempNum, tempNum1;//队伍在迭代时候保存临时变量的东西
		//--------------------初始化一些数据
		Scanner cin = new Scanner(System.in);
		System.out.print("输入队伍的数量: ");
		team_Num = cin.nextInt();
		if (team_Num % 2 != 0)//队伍个数为奇数时
		{
			empty = true;
			team_Num++;
		}
		round = team_Num - 1;
		jump = ((team_Num + 1) / 2) - 1;
		team_Arr = new int[team_Num];
		team_temp = new int[team_Num];
		for (int i = 0; i < team_Num; i++) {
			team_Arr[i] = i + 1;
		}
		if (empty) {
			team_Arr[team_Num - 1] = 0;
		}
		flag = team_Num - 1;
		//---------------------开始计算了--------------
		for (int j = 0; j < round; j++) {
			System.out.println("第" + (j + 1) + "轮:");
			for (int m = 0; m < team_Num / 2; m++) {
				System.out.println(team_Arr[m] + "----" + team_Arr[team_Num - m - 1]);
			}
			for (int g = 0; g < team_Num; g++) {
				team_temp[g] = team_Arr[g];
			}
			if (flag != 0) {
				tempNum = team_Arr[flag];//temp 一开始总是记录0队或者最大队伍
				flag = 0;//flag 跳动
				tempNum1 = team_Arr[flag];
				team_Arr[flag] = tempNum;

			} else {
				tempNum = team_Arr[flag];//temp 一开始总是记录0队或者最大队伍
				tempNum1 = team_Arr[team_Num - 1];
				flag = team_Num - 1;//flag 跳动
				team_Arr[flag] = team_temp[flag] = tempNum;
				team_Arr[0] = team_temp[0] = tempNum1;

			}
			for (int k = 0; k < team_Num - 1; k++) {//走动
				int t = k;
				if (t >= team_Num)
					t = t - team_Num;
				int z = t;

				for (int u = 0; u < jump; u++) {
					t++;
					if (t == team_Num)
						t = t - team_Num;
					if (t == flag)
						t++;
					if (t == team_Num)
						t = t - team_Num;
				}
				team_Arr[t] = team_temp[z];//
			}
		}
	}
}
