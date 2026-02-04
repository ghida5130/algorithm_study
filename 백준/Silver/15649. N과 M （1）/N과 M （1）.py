n, m = map(int, input().split())

visited = [0] * (n+1)

def dfs(visited, now):
    if len(now) == m:
        print(" ".join(map(str, now)))
    else:
        for k in range(1, n+1):
            if visited[k] == 0:
                visited[k] = 1
                now.append(k)
                dfs(visited, now)
                now.pop()
                visited[k] = 0

dfs(visited, [])