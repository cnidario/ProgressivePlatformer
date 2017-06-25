package org.hugo.platformer.math;

import com.badlogic.gdx.math.Vector;

import java.util.ArrayList;

public class Polynomial<T extends Vector> {
    private ArrayList<T> coefficients;

    public Polynomial() {
        coefficients = new ArrayList<T>();
    }
    public ArrayList<T> getCoefficients() {
        return coefficients;
    }

    public static Polynomial constant(Vector term) {
        Polynomial p = new Polynomial();
        p.getCoefficients().add(term);
        return p;
    }
    public Polynomial add(T term) {
        getCoefficients().add(term);
        return this;
    }
    public T eval(float x) {
        //hacemos ruffini
        T result = (T)coefficients.get(coefficients.size() - 1).cpy();
        for(int i = coefficients.size() - 2; i >= 0; i--) {
            result.scl(x).add(coefficients.get(i));
        }
        return result;
    }
    public Polynomial<T> derivate() {
        for(int i = 0; i < coefficients.size() - 1; i++) {
            coefficients.set(i, (T)coefficients.get(i + 1).scl(i+1));
        }
        coefficients.remove(coefficients.size() - 1);
        return this;
    }
    public Polynomial<T> add(Polynomial<T> q) {
        int pgrade = coefficients.size();
        int qgrade = coefficients.size();
        for(int i = 0; i < qgrade; i++) {
            if(i > pgrade) {
                coefficients.add(q.coefficients.get(i));
            } else {
                coefficients.get(i).add(q.coefficients.get(i));
            }
        }
        return this;
    }
    public Polynomial<T> sub(Polynomial<T> q) {
        int pgrade = coefficients.size();
        int qgrade = coefficients.size();
        for(int i = 0; i < qgrade; i++) {
            if(i > pgrade) {
                coefficients.add(q.coefficients.get(i));
            } else {
                coefficients.get(i).sub(q.coefficients.get(i));
            }
        }
        return this;
    }
    public Polynomial<T> scale(float k) {
        for(int i = 0; i < coefficients.size(); i++) {
            coefficients.get(i).scl(k);
        }
        return this;
    }
    public Polynomial klone() {
        Polynomial klon = new Polynomial();
        klon.coefficients = new ArrayList<Vector>();
        for(Vector v : coefficients) {
            klon.coefficients.add(v.cpy());
        }
        return klon;
    }
}
