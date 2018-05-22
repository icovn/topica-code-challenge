<?php
/**
 *
 * User: icovn
 * Date: 22/05/2018
 * Time: 11:17
 */

namespace net\friend\Persistence;
use net\friend\Model\Article;
use net\friend\Model\ArticleRepository;
class InMemoryArticleRepository implements ArticleRepository
{
    private $articles;
    public function __construct()
    {
        $this->articles = [
            1 => new Article(1, 'Hello world!', 'This article is here to welcome you.'),
            2 => new Article(2, 'There is something new!', 'Here is a another article.'),
        ];
    }
    public function getArticles()
    {
        return $this->articles;
    }
    public function getArticle($id)
    {
        return $this->articles[$id];
    }
}