package com.br.carlos.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.br.carlos.cursomc.domain.Categoria;
import com.br.carlos.cursomc.domain.Produto;
import com.br.carlos.cursomc.repositories.CategoriaRepository;
import com.br.carlos.cursomc.repositories.ProdutoRepository;
import com.br.carlos.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto buscar(Integer id) {
		 Optional<Produto> obj = repo.findById(id);
		 return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto  não encontrado! Id: "+id+", Tipo: "+
		 Produto.class.getName()));
	}
	
	
	
	public Page<Produto> search(String nome, List<Integer> ids,Integer page,Integer linesPerPage,String orderBy, String direction){
		PageRequest pageRequest =  PageRequest.of(page, linesPerPage, Direction.valueOf(direction),
				orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return repo.search(nome,categorias,pageRequest);
		
	}
	
	
}
