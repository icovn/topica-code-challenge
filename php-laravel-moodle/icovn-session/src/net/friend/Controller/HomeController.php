<?php
/**
 *
 * User: icovn
 * Date: 22/05/2018
 * Time: 11:16
 */

namespace net\friend\Controller;
use net\friend\Model\ArticleRepository;
use Twig_Environment;
class HomeController
{
    /**
     * @var ArticleRepository
     */
    private $repository;
    /**
     * @var Twig_Environment
     */
    private $twig;
    public function __construct(ArticleRepository $repository, Twig_Environment $twig)
    {
        $this->repository = $repository;
        $this->twig = $twig;
    }
    /**
     * Example of an invokable class, i.e. a class that has an __invoke() method.
     *
     * @see http://php.net/manual/en/language.oop5.magic.php#object.invoke
     */
    public function __invoke()
    {
        echo $this->twig->render('home.twig', [
            'articles' => $this->repository->getArticles(),
        ]);
    }
}