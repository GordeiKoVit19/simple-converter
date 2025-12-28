package com.example.simpleconverter.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ConversionViewModel extends ViewModel {

    private final MutableLiveData<Double> result = new MutableLiveData<>();

    public LiveData<Double> getResult() {
        return result;
    }

    public void convert(String unit, double value1, double value2) {
        double converted;
        switch (unit) {
            case "Метры → Сантиметры":
            case "Meters → Centimeters":
                converted = value1 * 100;
                break;
            case "Сантиметры → Метры":
            case "Centimeters → Meters":
                converted = value1 / 100;
                break;
            case "Минуты → Секунды":
            case "Minutes → Seconds":
                converted = value1 * 60;
                break;
            case "Секунды → Минуты":
            case "Seconds → Minutes":
                converted = value1 / 60;
                break;
            case "Площадь квадрата":
            case "Square Area":
                converted = value1 * value1;
                break;
            case "Периметр квадрата":
            case "Square Perimeter":
                converted = 4 * value1;
                break;
            case "Площадь прямоугольника":
            case "Rectangle Area":
                converted = value1 * value2;
                break;
            case "Периметр прямоугольника":
            case "Rectangle Perimeter":
                converted = 2 * (value1 + value2);
                break;
            default:
                converted = value1;
        }
        result.setValue(converted);
    }
}
