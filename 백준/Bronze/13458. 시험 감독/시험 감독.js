const readline = require("readline");

function solution(data) {
    const [n, ai, count] = data.split("\n");
    const st = ai.split(" ").map(Number);
    const [b, c] = count.split(" ").map(Number);
    let total = 0;

    for (let i = 0; i < Number(n); i++) {
        if (st[i] <= b) {
            total++;
            continue;
        }
        const nowSt = st[i] - b;
        const subTotal = Math.ceil(nowSt / c);
        total += subTotal + 1;
    }

    return total;
}

const input = [];
readline
    .createInterface({
        input: process.stdin,
        output: process.stdout,
    })
    .on("line", (line) => {
        input.push(line);
    })
    .on("close", () => {
        const data = input.join("\n");
        console.log(solution(data));
    });
