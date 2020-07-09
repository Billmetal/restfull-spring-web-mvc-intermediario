package br.com.digitalinnovationone.example.restful.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.cfg.PackageVersion;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.digitalinnovationone.example.restful.enums.Raca;

@Configuration
public class Jackson {

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		//PROPRIEDADES NÃO MAPEADAS NÃO QUEBRAM
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//FALHA SE ALGUMA PROPRIEDADE ESTIVER VAZIA
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
		/*SERVE PARA COMPATIBILIDADE DE ARRAYS , quando tem um array com um item,
		 * caso não tenha essa config ele se perde*/
		objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
		//SERELIZE DATAS
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.registerModule(racaModuleMapper());
		return objectMapper;
	}

	private SimpleModule racaModuleMapper() {
		SimpleModule dateModule = new SimpleModule("JSONRacaModule", PackageVersion.VERSION);
		dateModule.addSerializer(Raca.class, new RacaCerialize());
		dateModule.addDeserializer(Raca.class, new RacaDescerialize());
		return null;
	}
	
	class RacaCerialize extends StdSerializer<Raca>{

		public RacaCerialize() {
			super(Raca.class);
		}

		@Override
		public void serialize(Raca value, JsonGenerator gen, SerializerProvider provider) throws IOException {
			gen.writeString(value.getValue());
		}
	}
	
	class RacaDescerialize extends StdDeserializer<Raca>{
		
		public RacaDescerialize() {
			super(Raca.class);
		}

		@Override
		public Raca deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			return Raca.of(p.getText());
		}
	}
}
