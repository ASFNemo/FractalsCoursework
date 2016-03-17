

In a bid for brevity I have decided against splitting up the classes in to many smaller classes. Although individual
classes may be shorter over all there will be alot of duplication.


=================
EXTENSIONS: (and how to use them)
=================

1, The first and main extension I have added is the ability to view different fractals and simplicity of adding extra
   fractals. The 6 fractals are:
        - Mandelbrot: This is the basic fractal we were told to build. Equation: z1= (z0)^2 + c
        - BurningShip: This is the fractal that we are recomended to create as an extension in the spec.
            Equation: z1 = |(z0)^2 + c|
        - Quadrabrot: This is a fractal I found while trying out a bunch of different fractals and particularly liked
            the look of this fractals. Equation z1 = (z0)^4 + c
        - Bird of Prey: This was a cool fractal i found online and decided to implement. This meant I had to work out
          and implement how to cube complex numbers, but provides a really cool shaps so was totaly worth it.
          Equation: z1 = (z0)^3 + c
        - Burning Ship Variant: I accidentaly came across this one while orginaly trying to ake the Burning ship fractal
          and thought it was so cool I had to add it. This isn't the pretiest but when you zoom in with the Fiery red
          colors it looks mesmerising.
        - Random Multibrot: After discovering the Quadrabrot (above) I decided to try other multiples of the mandelbrot
          they we so cool that i decided to add one where it generates a fractal that has been squared a random number
          of times. Equation: Z1 = (z0)^x + c
   I have alsow added some methods (such as the multiply methdod) that although not directly needed bu any of the
   fractals I am making can be used to make other fractals very simply.
   For simplicity of adding fractals, julia sets and other fractals features I have also added aditional constructors
   that can be called.
2, The second Extension I added was giving the user the options of a few different colouring algorithms. The main
   motivation behind this was that the fractals had so much intricacy that just one color system was not enough, allowing
   the user to choose from and switch through many coloring algirthms would allow the user to see all the details of
   of these fractals in and there intricacies
3, The third extension I made was the ability to get live julia set updates. This is to allow the user to see how the
   julia set changes as ou move around the mandelbrot. The user can do this by pressing 'z' and moving the mouse around
   the fractal
4, The fourth extension I have added is the ability to zoom in or out using the '+' and '-' keys. This is part of my aim
   for the user to have the ability to move around the mandelbrot without having to use the mouse. The zoom will into
   the spot that is currently at the center of the madelbrot panel
5, move around fractal with up down/ left and right
5, The fifth extension I have added is the ability to move around the mandelbrot panel using the four arrows. This was
   to allow the user to move around without using a mouse. This will allow to move around only when they zoomed in as it
   would be slightly pointless to be able to move around outside the mandelbrot space and a user may loose the mandelbot
   which may aggravate them.