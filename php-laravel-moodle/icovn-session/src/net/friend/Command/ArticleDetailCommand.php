<?php
/**
 *
 * User: icovn
 * Date: 22/05/2018
 * Time: 11:19
 */

namespace net\friend\Command;
use net\friend\Model\ArticleRepository;
use Symfony\Component\Console\Output\OutputInterface;
class ArticleDetailCommand
{
    /**
     * @var ArticleRepository
     */
    private $repository;
    public function __construct(ArticleRepository $repository)
    {
        $this->repository = $repository;
    }
    public function __invoke($id, OutputInterface $output)
    {
        $article = $this->repository->getArticle($id);
        $output->writeln('<info>' . $article->getTitle() . '</info>');
        $output->writeln($article->getContent());
    }
}