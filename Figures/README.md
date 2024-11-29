# 2D Figures Program

## Introduction

This project implements a program that works with 2D geometric figures. Initially, it supports a limited set of figures, but the program is designed to be easily extensible, allowing for the addition or removal of figures in the future.

### Supported Figures

- **Triangle** — Defined by three sides, specified by their lengths.
- **Circle** — Defined by a single radius.
- **Rectangle** — Defined by two sides, specified by their lengths.
- **Infinite many others 2D** - just make a constructor for new one
- **Hexagon** - (is the template example)

For all figures, it is possible to calculate their perimeter. Figures in this program are immutable. The specific properties of the figures (e.g., sides of a triangle, radius of a circle) cannot be accessed directly, but the program provides functionality to calculate their perimeter.

### String Representation of Figures

All figures can be represented as a string. The format is as follows:
- **Triangle**: `"triangle 10 20 30"` — A triangle with sides 10, 20, and 30.
- **Circle**: `"circle 2.2"` — A circle with a radius of 2.2.
- **Rectangle**: `"rectangle 4 5"` — A rectangle with side lengths of 4 and 5.

Your solution should allow the user to:
- Create a figure from its string representation.
- Convert a figure to a string.

---

## User Input Methods

When the program starts, the user is prompted to choose how they would like to create new figures. The following methods are available:

1. **Random** — Figures are created using a random number generator.
2. **STDIN** — Figures are read from the standard input, with each figure represented in the string format mentioned above.
3. **File** — Figures are read from a text file specified by the user. Each line in the file should represent one figure in the string format.

---

## Operations on Figures

After figures are entered into the program using one of the above methods, the following operations are available:

- **List all figures** — Display all entered figures to the standard output.
- **Delete a figure** — Remove a figure from the list.
- **Duplicate a figure** — Create a copy of a figure, which is added to the end of the list.
- **Store the list** — Save the resulting list of figures back into a file.

---

## Future Extensions

The program is designed with extensibility in mind. Adding new types of figures or modifying existing ones can be done with minimal changes to the code. Similarly, new input methods can be integrated easily.

---

## Example Use Case

1. **User selects input method**:
   - `Random` (generates random figures).
   - `STDIN` (enters figure definitions manually).
   - `File` (loads figure definitions from a text file).

2. **Operations**:
   - List, delete, duplicate, or store the figures based on the user's selection.

3. **Store Results**: After performing the desired operations, the list of figures can be saved back to a file.

---

## License

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT).
