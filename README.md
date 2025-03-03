# Expense Tracker CLI

Expense Tracker CLI es una herramienta de línea de comandos sencilla para gestionar tus gastos. Puedes agregar, actualizar, eliminar, listar y exportar gastos desde la terminal, además de establecer presupuestos mensuales y recibir advertencias si los excedes.

## **Características**

- Añadir gastos con descripción, monto, fecha y categoría.
- Actualizar atributos de un gasto existente.
- Eliminar gastos por su identificador único.
- Listar todos los gastos registrados.
- Mostrar un resumen general de los gastos.
- Resumen específico de gastos por mes.
- Establecer presupuestos mensuales y recibir advertencias al excederlos.
- Exportar los gastos a un archivo CSV.

---

## **Requisitos Previos**

Antes de ejecutar la aplicación, asegúrate de tener lo siguiente:

1. **Java Development Kit (JDK)** instalado (versión 8 o superior).
2. Configurada la variable de entorno `JAVA_HOME`.
3. Herramienta `Maven` para compilar el proyecto (opcional, si usas Maven).

---

## **Cómo Usar**

### **Compilación**
1. Clona el repositorio:
   ```bash
   git clone <URL_DEL_REPOSITORIO>
   cd expense-tracker-cli
2. Compila el proyecto:
   ```bash
   javac -d out src/org/expensetracker/*.java
3. Ejecuta el programa
   ```bash
   java -cp out org.expensetracker.ExpenseCLI <command>

## **Ejemplo de Uso**
1. Agregar un gasto
   ```bash
   java -cp out org.expensetracker.ExpenseCLI add
2. Borrar un gasto
   ```bash
   java -cp out org.expensetracker.ExpenseCLI remove
3. Actualizar un gasto
   ```bash
   java -cp out org.expensetracker.ExpenseCLI update
4. Listar los gastos
   ```bash
   java -cp out org.expensetracker.ExpenseCLI list
5. Resumen de los gastos
    ```bash
   java -cp out org.expensetracker.ExpenseCLI summary
6. Resumen de los gastos por mes
    ```bash
   java -cp out org.expensetracker.ExpenseCLI summary-month
7. Exportar los gastos a CSV
  ```bash
   java -cp out org.expensetracker.ExpenseCLI export
  ```
8. Establecer presupuesto a un mes
   ```bash
   java -cp out org.expensetracker.ExpenseCLI budget

---

## **Notas Importantes**

### **Formato de Fecha**
- El programa utiliza el formato `YYYY-MM-DD` para registrar y exportar fechas.

### **Categorías de Gastos**
Algunas categorías comunes son:
- **Fijos**
- **Variable**
- **Otros**

### **Presupuestos Mensuales**
- Los presupuestos se asignan por mes utilizando su número:
  - `1` para enero
  - `2` para febrero
  - Y así sucesivamente.

### **Archivos CSV**
- Los gastos exportados se guardan en un archivo CSV con las siguientes columnas:
  - `ID`
  - `Descripción`
  - `Cantidad`
  - `Categoría`
  - `Fecha`
