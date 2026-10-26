import hashlib as h

ki = "1234567890ABCDEF"
rand = "ABCDEF1234567890"

def a3(ki, rand):
    data = ki + rand
    hash_value = h.sha256(data.encode()).hexdigest()
    return hash_value[:8]

def a8(ki, rand):
    data = ki + rand
    hash_value = h.sha256(data.encode()).hexdigest()
    return hash_value[8:24]

def a5(message, kc):
    encrypted = ""
    for i in range(len(message)):
        encrypted += chr(ord(message[i]) ^ ord(kc[i % len(kc)]))
    return encrypted

print(ki)
print(rand)
print(a3(ki,rand))
kc = a8(ki, rand)
print(kc)
print(a5("Hello",kc))