Para poder hacer la calculadora hice un checklist viendo como funcionaba la calculadora de mi telefono y poder asi hacer la logica mas facil: 

Mi primer paso fue: si estoy ingresando valores desde los botones, mi programa debe de identificar cual es un numero y cual es un operador, esto lo puse en una funcion que se llamaba antes identificarInput
pero le cambie el nombre a crearExpresion porque importe implementation ("net.objecthunter:exp4j:0.4.8") que ya me permitia traerme la clase de ExpressionBuilder  que tiene toma como parametro
mi expresion que es una cadena string que con el metodo build(), devuelve un objeto Expression que es la expresion matematica que va poder ser evaluada con el 
metodo de la clase que es evaluate()

Pero antes de evaluar la expresion para crear mi expresion o identificar mis inputs lo que hice fue dividirlos en numeros, operadores y puntos, y anote ciertas acciones a realizar si se identificaba cada tipo
Algunas de ellas las complete y algunas otras me falto pulirlas o investigar si ya existen clases que resuelven esto como por ejemplo el tema de los exponenciales.
// CheckList:
// Si input=numero; se puede ingresar un o una secuencia de numeros despues de este

// Si input=operador:
// solo se puede ingresar un o una secuencia de numeros despues de este
// si se agrega otro operador el primer operador se sustituye por el segundo en escribirse.

// Si input=. :
// No permitir hacer 9.9.9 +3.3.3+1.2323.31 --Esto esta incorrecto
//No permitir meter dos o mas puntos consecutivos ...--Esto esta correcto
//Permitir .+ y sustituir el punto por el operador
//No permitir  ingresar un punto despues de un operador +.  --Esto esto se puede mejorar (no es tan importante)

// si la longitud de valores numericos consecutivos es mayor a 9 colocar una e y empezar a contar cuantos valores se estan ingresando despues -->mejorarlo empieza en e9 hasta e14
//si tenemos un valornumerico y despues de este tenemos un operador ydespues otro valor numerico, hacer operacion matematica--Resuelto con la importacion de ExpressionBuilder![image](https://github.com/user-attachments/assets/5a536915-f520-4bcd-9745-09fac3391dbf)
