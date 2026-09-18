def balistica ( x ):
    return - x*x - 4*x - 4;

def graficar (fun_graf):
    for i in range (0,11):
        print (fun_graf(i/10))

#-------------
graficar(balistica)