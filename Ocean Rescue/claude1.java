import java.util.*;
import java.io.*;

public class claude1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();

        while(t-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            char[][] g = new char[n][];
            for(int i=0;i<n;i++) g[i] = br.readLine().toCharArray();

            int[][] dist = new int[n][m];
            for(int[] row : dist) Arrays.fill(row, -1);

            ArrayDeque<int[]> q = new ArrayDeque<>();
            for(int i=0;i<n;i++)
                for(int j=0;j<m;j++)
                    if(g[i][j]=='B'){ dist[i][j]=0; q.add(new int[]{i,j}); }

            int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
            int ans = 0;

            while(!q.isEmpty()){
                int[] cur = q.poll();
                int x = cur[0], y = cur[1];
                for(int dir=0; dir<4; dir++){
                    int nx = x+dx[dir], ny = y+dy[dir];
                    if(nx>=0 && nx<n && ny>=0 && ny<m && dist[nx][ny]==-1 && g[nx][ny]!='#'){
                        dist[nx][ny] = dist[x][y]+1;
                        if(g[nx][ny]=='S') ans = Math.max(ans, dist[nx][ny]);
                        q.add(new int[]{nx,ny});
                    }
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.print(sb);
    }
}