package com.bolsadeideas.springboot.backend.apirest.auth;

public class JwtConfig {
	public static final String LLAVE_SECRETA = "inserte.clave";
	
	public static final String RSA_PRIVADA= "-----BEGIN RSA PRIVATE KEY-----\r\n"
			+ "MIIEowIBAAKCAQEAs0Gjnk0TzFUJowGXCt2sSW/iX5lZbdIS2yhETVpNBN/hAD5G\r\n"
			+ "MIk9udURqlgBc4Rsew/hIJBn7Nhvh+obmUqQ5MU+t4S5dtUjJBvbdvgUiAAX1i4j\r\n"
			+ "tH0x0e0KmwF5/jBXpUj84lSrPGprAPXDBLZvO6TwcdAgFuyVGzCsfAW9LC3rgyI2\r\n"
			+ "+6CkXYho8M+1cuk2UBqaVlSOpjKXN6lcCbc89E/3v2s7LJRqIra1+bBlwxEqxBFz\r\n"
			+ "zzwiG3Dl+qsOoLgq7K2B+p17R8ukgSl3zfNtj9Lk4byv5ASh7EULG23odDIK/Liq\r\n"
			+ "QwYo3oBk9w3tu6+5PvDjPfL0+MmUQUbFo+bFtQIDAQABAoIBAEJcfHwwQLIWkTi9\r\n"
			+ "b/Nf2VZei0mk9cyIRt7Ha3HPik/RqfRzGX0Ccc2IKcAMUI05awer+0YY78Bo5/FD\r\n"
			+ "I/B+AGIQ+4TpJ9fsU9QcVAUZFlC66BgsgJ9i8byrqo9z3nvR3cCc4rHPco0tQGvc\r\n"
			+ "j9QAzfEgwLsBt7+E2jZ0sIZBgaUGxDSHP+6QWF+ZtdkIjNNRkMnTAttKizqEy3zY\r\n"
			+ "TfJM3TCnxURRWFFvQFb8GlJ+3WWyLUSabfYxw4YDMSWakDBnH9W0mE8S02aw8VxF\r\n"
			+ "36qbhBG8RK1yXHfEnm6VCOzGfNvmI/BeUUmLyCx+GQyaJbVujSzSdFHjQTjzVB0m\r\n"
			+ "+3vT9kkCgYEA38GjYIEUl+2zYfvQimWvqMEkpSJQv6k6xtdyZrvKqPYvbijKscT4\r\n"
			+ "5NpVqSfHEIkq7YNFN3THZmk2dZMd+4K8B3IC2cJv0Ij3RM0j/W8nb7X5aex1zHxu\r\n"
			+ "Wim7qlvTZ7ryGnXVIGNpGSXNjqhxVxBKHC2eXHfaa0CZKsuGfp2JqJcCgYEAzRZl\r\n"
			+ "/qkjtKYr3yYkLysKByj+TrKYxNOQUzJnAFW5QtX1QZAkXDcN292dU9M6eZSlhwVV\r\n"
			+ "NYfPJCiAuIao7snrAB956LwUvUdngM0JVypdV3ZIPtFT3hXYA5BGZv82y5JeGO8H\r\n"
			+ "ewS465ce89KegXHDEnhUb03ACqKjHc0AEKJMoZMCgYEAvxwdVaSKGUVtjAfO0xYM\r\n"
			+ "1T659Mz9WOXKjOChFnvYmT1Dv3vZu8H3r0eIGGUgEdCGOLyA1t/IG4FLgVp5JkNA\r\n"
			+ "I7/ODX2TkPIa98marrBMZ45ui3jqGBBDFIFGhzfMtXEnrU/tC6+8qTzvd6QIwgro\r\n"
			+ "sW6JLoMzq51N9Q5GPkiBtdsCgYAkpDTS2+tKMkoyU0ExwDHJd7R/4P7UY6wsgRvP\r\n"
			+ "/oDcBRgNf7BPXXK3bo/WUy3mXP158yT3LG2A20+sKDr0dtTqr7T7NOdLrf87Dub1\r\n"
			+ "UNdecf6UYpfmXOa8NEGv0zc6BcuUDti6TOyxAs3/6AhzvhyJJ0CGplCCVe5ou6Cg\r\n"
			+ "WC7yywKBgHM6kD+ByFPp/S+/bROYD+TrPpuyXgYrHkq9OJ/t1lNgCBjvoc8+wqr6\r\n"
			+ "Lz2GII+C1cE5MgUUQVM0GG9VXhBR30MsexlN2mv4sI4K5VB2epvKkCiwzBwrbgkB\r\n"
			+ "GXnW7Z1uix8313Gl3oj/F2JBxdiUj5g4z0npgAMe69oB7uR/SPhl\r\n"
			+ "-----END RSA PRIVATE KEY-----";
	
	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\r\n"
			+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAs0Gjnk0TzFUJowGXCt2s\r\n"
			+ "SW/iX5lZbdIS2yhETVpNBN/hAD5GMIk9udURqlgBc4Rsew/hIJBn7Nhvh+obmUqQ\r\n"
			+ "5MU+t4S5dtUjJBvbdvgUiAAX1i4jtH0x0e0KmwF5/jBXpUj84lSrPGprAPXDBLZv\r\n"
			+ "O6TwcdAgFuyVGzCsfAW9LC3rgyI2+6CkXYho8M+1cuk2UBqaVlSOpjKXN6lcCbc8\r\n"
			+ "9E/3v2s7LJRqIra1+bBlwxEqxBFzzzwiG3Dl+qsOoLgq7K2B+p17R8ukgSl3zfNt\r\n"
			+ "j9Lk4byv5ASh7EULG23odDIK/LiqQwYo3oBk9w3tu6+5PvDjPfL0+MmUQUbFo+bF\r\n"
			+ "tQIDAQAB\r\n"
			+ "-----END PUBLIC KEY-----";


}
