package com.sofkau.dialogo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MensajeOperacionBd {

    private static final Logger logger = LoggerFactory.getLogger(MensajeOperacionBd.class);

    public static void crearAutor(){
        logger.info(ConstantesInserccionBd.MSN_CREARAUTOR);
    }

    public static void crearError(){
        logger.error(ConstantesInserccionBd.MSN_ERRORCREARAUTOR);
    }

    public static void crearLibro(){
        logger.info(ConstantesInserccionBd.MSN_CREARlIBRO);
    }

    public static void crearNovela(){
        logger.info(ConstantesInserccionBd.MSN_CREARNOVELA);
    }

    public static void actualizarLibroExitoso() {
        logger.info(ConstantesInserccionBd.MSN_ACTUALIZARLIBRO);
    }

    public static void actualizarNovelaExitoso() {
        logger.info(ConstantesInserccionBd.MSN_ACTUALIZARNOVELA);
    }

    public static void errorActualizarLibro() {
        logger.error(ConstantesInserccionBd.MSN_ERRORACTUALIZARLIBRO);
    }

    public static void errorActualizarNovela() {
        logger.error(ConstantesInserccionBd.MSN_ERRORACTUALIZARNOVELA);
    }

    public static void crearUsuario() {
        logger.info(ConstantesInserccionBd.MSN_CREARUSUARIO);
    }

    public static void crearPrestamo() {
        logger.info(ConstantesInserccionBd.MSN_CREARPRESTAMO);
    }

    public static void errorActualizacionPrestamo() {
        logger.error(ConstantesInserccionBd.MSN_PRESTAMOERROR);
    }

    public static void prestamoActualizado() {
        logger.info(ConstantesInserccionBd.MSN_PRESTAMOACTUALIZADO);
    }




}
