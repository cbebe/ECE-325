#include <stdint.h>
#include <stdio.h>

uint64_t fnv(void *b, int c) {
    unsigned char *p = b;
    uint64_t h = 14695981039346656037ULL;
    for (int i = 0; i < c; ++i) h = (h * 1099511628211ULL) ^ p[i];

    return h;
}

int main() {
    unsigned char b[] = {0xAA, 0xBB, 0xCC, 0xDD, 0xEE};
    printf("%llu\n", fnv(b, 5));
}