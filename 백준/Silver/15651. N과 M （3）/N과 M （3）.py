n, m = map(int, input().split())


def dfs(now):
    if len(now) == m:
        print(" ".join(map(str, now)))
    else:
        for k in range(1, n+1):
            now.append(k)
            dfs(now)
            now.pop()


dfs([])