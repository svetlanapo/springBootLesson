package ru.spo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.spo.domain.Product;
import ru.spo.service.ProductServise;

import java.util.List;
import java.util.stream.IntStream;


@Controller
@RequestMapping("/")
public class ProductController {
    @Autowired
    private ProductServise productServise;
    private List<Product> productList;

    @GetMapping("/")
    public String listProducts(Model model,
//                          @RequestParam(value = "size", required = false, defaultValue = "0") int size,
                               @RequestParam(value = "page", required = false, defaultValue = "0") int page)
    { Page<Product> productPage = productServise.findPaginated(page, 5);
    model.addAttribute("productPage", productPage);
    model.addAttribute("numbers", IntStream.range(0,productPage.getTotalPages()).toArray());
    return "products";
    }

   @RequestMapping("/edit/{id}")
   public ModelAndView editProduct(@PathVariable long id) {
       ModelAndView model = new ModelAndView("edit_product");
       Product product = productServise.getById(id);
       model.addObject("product", product);
       return model;
   }

    @GetMapping("/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "new_product";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product) {
        productServise.save(product);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        productServise.delete(id);
        return "redirect:/";
    }

    @RequestMapping("/filter" )
    public String getProductsByFilter(Model model,
                                      @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "minPrice", defaultValue = "0.0") double minPrice,
            @RequestParam(name = "maxPrice", defaultValue = "9999999") double maxPrice
    ) {
        Page<Product> productPage = productServise.getFilterByPrice(minPrice, maxPrice, page, 8);
        model.addAttribute("productPageFiltered", productPage);
        model.addAttribute("numbers", IntStream.range(0,productPage.getTotalPages()).toArray());
        return "productsFiltered";
    }




}
