#include <stdint.h>
#include <stdio.h>

uint64_t fnv(void *b, int c) {
    unsigned char *p = b;
    uint64_t h = 14695981039346656037ULL;
    for (int i = 0; i < c; ++i) h = (h * 1099511628211ULL) ^ p[i];

    return h;
}

size_t get_str_len(char *str) {
    size_t len = 0;
    while ((int)str[len] > 0) len++;
    return len;
}

int main(int argc, char **argv) {
    printf("%s\n", argv[1]);
    printf("%llu\n", get_str_len(argv[1]));
}
