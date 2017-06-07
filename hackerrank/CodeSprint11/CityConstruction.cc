#define N 50005
#define inf 1000000007
using namespace std;

struct Link_Cut_Tree{
    int fa[N],c[N][2],q[N],top;bool rev[N];
    Link_Cut_Tree(){
        top=0;memset(fa,0,sizeof(fa));
        memset(c,0,sizeof(c));
        memset(rev,0,sizeof(rev));
    }
    inline bool isroot(int x){return c[fa[x]][1]!=x&&c[fa[x]][0]!=x;}
    inline void pushdown(int x){
        int l=c[x][0],r=c[x][1];
        if(rev[x]){
            rev[x]^=1;rev[l]^=1;rev[r]^=1;
            swap(c[x][1],c[x][0]);
        }
    }
    void rotate(int x){
        int y=fa[x],z=fa[y],l,r;
        l=(c[y][1]==x);r=l^1;
        if(!isroot(y))c[z][c[z][1]==y]=x;
        fa[c[x][r]]=y;fa[y]=x;fa[x]=z;
        c[y][l]=c[x][r];c[x][r]=y;
    }    
    void splay(int x){
        int top=0;q[++top]=x;
        for(int i=x;!isroot(i);i=fa[i])q[++top]=fa[i];
        while(top)pushdown(q[top--]);
        while(!isroot(x)){
            int y=fa[x],z=fa[y];
            if(!isroot(y)){
            if(c[y][0]==x^c[z][0]==y)rotate(x);else rotate(y);
        }
        rotate(x);
        }
    }
    void access(int x){for(int t=0;x;t=x,x=fa[x])splay(x),c[x][1]=t;}
    void makeroot(int x){access(x);splay(x);rev[x]^=1;}
    void link(int x,int y){makeroot(x);fa[x]=y;splay(x);}
    void split(int x,int y){makeroot(x);access(y);splay(y);}
    void cut(int x,int y){split(x,y);c[y][0]=fa[x]=0;}
    int find(int x){
        access(x);splay(x);int y=x;while(c[y][0])y=c[y][0];return y;
    }
}T;

int main(){
    int n, m, x, y, q;
    scanf("%d %d", &n, &m);
    for(int i=1;i<=m;i++){
        scanf("%d %d", x, y);
        T.link(x,y);
    }
    scanf("%d", &q);
    for (int i=1;i<q;i++) {
        scanf("%d %d %d", &m, &x, &y);
        if (m == 1) {
            n++;
            if (y == 0) T.link(x, n);
            else T.link(n, x);
        } else {
            int xx=T.find(x),yy=T.find(y);
            if(xx==yy)puts("Yes");else puts("No");
        }
    }
    return 0;
}
