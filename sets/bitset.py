class BitSet(object):

    def __init__(self, bits=0):
        self._bits = 0

    def add(self, x):
        self._bits |= 1 << x

    def remove(self, x):
        self._bits &= ~(1 << x)

    def contains(self, x):
        return self._bits & (1 << x) != 0

    def bits(self):
        return self._bits


def union(a, b):
    return BitSet(bits=a.bits()|b.bits())


def intersection(a, b):
    return BitSet(bits=a.bits()&b.bits())
