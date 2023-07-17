package br.com.uanderson.blackboxannotations.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UploadUtil {

    public static boolean fazerUploadImagem(MultipartFile imagem){
        Path diretorioRaiz = Paths.get("src");
        Path subDiretorios = Paths.get("src/main/resources/static/imagens/img-uploads");
        String nomeArquivo = imagem.getOriginalFilename();
        boolean sucessoUpload = false;

        if (!imagem.isEmpty()) {
            //CRIANDO O DIRETÓRIO PARA ARMAZENAR AS IMAGENS
            try {
                if (Files.notExists(diretorioRaiz)){
                    Files.createDirectory(diretorioRaiz);//CRIANDO O DIRETÓRIO RAIZ
                }
                Path directories = Files.createDirectories(subDiretorios);//CRIANDO OS SUB-DIRETÓRIOS
                Path filePath = Paths.get(subDiretorios.toString(), nomeArquivo);//PEGANDO O NOME DO ARQUIVO
                Path filePathCreated = Files.createFile(filePath);//CRIANDO O ARQUIVO

                sucessoUpload = true;
            }catch (IOException e){
                e.printStackTrace();
            }
        }else {
            System.out.println("Imagem vazia!");
        }
        
        return sucessoUpload;
    }//method
    
    
}
