#include <stdio.h>

int main() {
	int e, s, m, a=0;
	scanf("%d %d %d", &e, &s, &m);

	while (1) {
		a++;
		if (e == 1 && s == 1 && m == 1) break;
		if (e != 1) {
			e -= 1;
		}
		else {
			e = 15;
		}
		if (s != 1) {
			s -= 1;
		}
		else {
			s = 28;
		}
		if (m != 1) {
			m -= 1;
		}
		else {
			m = 19;
		}
	}

	printf("%d", a);
}