<?php
/**
 *
 * User: icovn
 * Date: 22/05/2018
 * Time: 11:19
 */

namespace net\friend\Model;
class Article
{
    private $id;
    private $title;
    private $content;
    public function __construct($id, $title, $content)
    {
        $this->id = $id;
        $this->title = $title;
        $this->content = $content;
    }
    public function getId()
    {
        return $this->id;
    }
    public function getTitle()
    {
        return $this->title;
    }
    public function getContent()
    {
        return $this->content;
    }
}